package com.example.jacky.kotlinstudy.coroutines

import android.os.Build
import android.support.annotation.RequiresApi
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by jacky on 2017/9/19.
 * banker developer. <br/>
 * <br/>
 */

class StandaloneCoroutine(override val context: CoroutineContext): Continuation<Unit> {
    override fun resume(value: Unit) {}

    override fun resumeWithException(exception: Throwable) {
        //处理异常
        val currentThread = Thread.currentThread()
        currentThread.uncaughtExceptionHandler.uncaughtException(currentThread, exception)
    }
}

fun launch(context: CoroutineContext, block: suspend () -> Unit) =
        block.startCoroutine(StandaloneCoroutine(context))

@RequiresApi(Build.VERSION_CODES.N)
suspend fun <T> CompletableFuture<T>.await(): T {
    return suspendCoroutine {
        continuation ->
        whenComplete { result, e ->
            if (e == null) continuation.resume(result)
            else continuation.resumeWithException(e)
        }
    }
}










































































































