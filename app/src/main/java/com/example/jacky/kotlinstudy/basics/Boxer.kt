package com.example.jacky.kotlinstudy.basics

/**
 * Created by jacky on 2017/9/13.
 * banker developer. <br/>
 * <br/>
 */

fun main(args: Array<String>) {

    val a: Int = 1000
    println(a === a) // true，值相等，对象地址相等

    //经过了装箱，创建了两个不同的对象
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    //虽然经过了装箱，但是值是相等的，都是100
    println(boxedA === anotherBoxedA) //  true，值相等，128 之前对象地址一样
    println(boxedA == anotherBoxedA) // true，值相等
test13(object :MyFun<String,String>{
    override fun inParam(t: String) {
        println("inParam")
    }

    override fun outValue(): String {
        println("outValue")
        return  "outValue"
    }

    override fun testp(t: String): String {
        println("testp")
        return  "testp"
    }

})
}

/*
这里我把 a 的值换成 100，这里应该跟 Java 中是一样的，
在范围是 [-128, 127] 之间并不会创建新的对象，比较输出的都是 true，
从 128 开始，比较的结果才为 false。
 */