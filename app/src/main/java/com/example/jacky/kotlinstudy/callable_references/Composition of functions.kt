package com.example.jacky.kotlinstudy.callable_references

/**
 * Created by jacky on 2017/9/13.
 * banker developer. <br/>
 * <br/>
 */

/**
 * The composition function return a composition of two functions passed to it:
 * compose(f, g) = f(g(*)).
 * Now, you can apply it to callable references.
 */

fun main(args: Array<String>) {
    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")
    println(strings.filter(oddLength))
}

private fun isOdd(x: Int) = x % 2 != 0
fun length(s: String) = s.length

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}
