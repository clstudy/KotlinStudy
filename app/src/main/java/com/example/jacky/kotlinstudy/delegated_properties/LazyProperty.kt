package com.example.jacky.kotlinstudy.delegated_properties

/**
 * Created by jacky on 2017/9/13.
 * banker developer. <br/>
 * <br/>
 */

class LazySample {
    val lazy: String by lazy {
        println("computed!")
        "my lazy"
    }
}

fun main(args: Array<String>) {
    val sample = LazySample()
    println("lazy = ${sample.lazy}")
    println("lazy = ${sample.lazy}")
}