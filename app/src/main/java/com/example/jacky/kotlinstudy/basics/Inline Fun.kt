package com.example.jacky.kotlinstudy.basics

/**
 * Created by jacky on 2017/9/17.
 * banker developer. <br/>
 * <br/>
 */
inline fun <T> lock(body: () -> T): T {

    print("lock.lock()  ")
    try {
        return body()
    } finally {
        println("  lock.unlock()")
    }
}

fun main(args: Array<String>) {
    println("开始************")
    lock { foo() }
    println("结束************")
}

fun foo() {
    print("foo exec")
}

//---------------------------------------------------------
/**
 * 调用Iterable<T>的forEach函数
 */
fun hasZeros(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) return true // 从 hasZeros 返回
    }
    return false
}


/**
 * crossinline
 */
inline fun f(crossinline body: () -> Unit) {
    val f = object: Runnable {
        override fun run() = body()
    }
    // ……
}

/**
 * 禁用内联noinline
 */
inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
    // ……
}


/**
 * reified
 */
//fun <T> TreeNode.findParentOfType(clazz: Class<T>): T? {
//    var p = parent
//    while (p != null && !clazz.isInstance(p)) {
//        p = p.parent
//    }
//    @Suppress("UNCHECKED_CAST")
//    return p as T?
//}
//
//inline fun <reified T> TreeNode.findParentOfType(): T? {
//    var p = parent
//    while (p != null && p !is T) {
//        p = p.parent
//    }
//    return p as T?
//}

inline fun <reified T> membersOf() = T::class.members