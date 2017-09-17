package com.example.jacky.kotlinstudy.basics

/**
 * Created by jacky on 2017/9/17.
 * banker developer. <br/>
 * <br/>
 */
sealed class Expr

data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}

fun main(args: Array<String>) {
    val e1 = Const(1.0)
    val e2 = Const(5.toDouble())
    val sum1 = Sum(e1, e2)
    println(eval(e1))
    println(eval(e2))
    println(eval(sum1))
}


