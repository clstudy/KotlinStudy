package com.example.jacky.kotlinstudy.coroutines

import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinWorkerThread
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.*

/**
 * Created by jacky on 2017/9/19.
 * banker developer. <br/>
 * <br/>
 */

fun main(args: Array<String>) {
    log("before coroutine")
    //启动我们的协程 
    asyncCalcMd5("test.zip") {
        log("in coroutine. Before suspend.")
        //暂停我们的线程，并开始执行一段耗时操作 
        val result: String = suspendCoroutine { continuation ->
            log("in suspend block.")
            executor.submit {
                continuation.resume(calcMd5(continuation.context[FilePath]!!.path))
                log("after resume.")
            }
        }
        log("in coroutine. After suspend. result = $result")
        executor.shutdown()
    }
    ContinuationInterceptor
    log("after coroutine")
    //加这句的原因是防止程序在协程运行完之前停止
    MyThreadPool.pool.awaitTermination(10000, TimeUnit.MILLISECONDS)
}

fun log(s: String) {
    println("[test coroutines tag=]    $s")
}

private val executor = Executors.newSingleThreadScheduledExecutor {
    Thread(it, "scheduler")
}

/**
 * 上下文，用来存放我们需要的信息，可以灵活的自定义
 */
class FilePath(val path: String) : AbstractCoroutineContextElement(FilePath) {
    companion object Key : CoroutineContext.Key<FilePath>
}

fun asyncCalcMd5(path: String, block: suspend () -> Unit) {
    val continuation = object : Continuation<Unit> {
        override val context: CoroutineContext
            get() = FilePath(path) + MyThreadPool

        override fun resume(value: Unit) {
            log("resume: $value")
        }

        override fun resumeWithException(exception: Throwable) {
            log(exception.toString())
        }
    }
    block.startCoroutine(continuation)
}

fun calcMd5(path: String): String {
    log("calc md5 for $path.")
    //暂时用这个模拟耗时 
    Thread.sleep(1000)
    //假设这就是我们计算得到的 MD5 值 
    return System.currentTimeMillis().toString()
}

object MyThreadPool : Pool(ForkJoinPool.commonPool())

open class Pool(val pool: ForkJoinPool)
    : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
            PoolContinuation(pool,
                    //下面这段代码是要查找其他拦截器，并保证能调用它们的拦截方法
                    continuation.context.fold(continuation, { cont, element ->
                        if (element != this@Pool && element is ContinuationInterceptor)
                            element.interceptContinuation(cont) else cont
                    }))
}

private class PoolContinuation<T>(
        val pool: ForkJoinPool,
        val continuation: Continuation<T>
) : Continuation<T> by continuation {

    override fun resume(value: T) {
        if (isPoolThread()) continuation.resume(value)
        else pool.execute { continuation.resume(value) }
    }

    override fun resumeWithException(exception: Throwable) {
        if (isPoolThread()) continuation.resumeWithException(exception)
        else pool.execute { continuation.resumeWithException(exception) }
    }

    fun isPoolThread(): Boolean = (Thread.currentThread() as? ForkJoinWorkerThread)?.pool == pool
}