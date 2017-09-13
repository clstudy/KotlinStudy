package com.example.jacky.kotlinstudy.callable_references

/**
 * Created by jacky on 2017/9/13.
 * banker developer. <br/>
 * <br/>
 */

/**
 * "Callable References" or "Feature Literals", i.e. an ability to pass
 * named functions or properties as values. Users often ask
 * "I have a foo() function, how do I pass it as an argument?".
 * The answer is: "you prefix it with a `::`".
 */

fun main(args: Array<String>) {
    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))
}

private fun isOdd(x: Int) = x % 2 != 0