package com.example.jacky.kotlinstudy.basics

/**
 * Created by jacky on 2017/9/17.
 * banker developer. <br/>
 * <br/>
 *
 * 扩展不能真正修改类,即没有在一个类中插入新成员!
 * 扩展函数是静态解析分发的,不是虚函数(即没有多态),调用只取决于对象的声明类型!
 */


/**
 * 1.调用是由对象声明类型决定,而不是由对象实际类型决定!
 */
open class C

class D : C()

fun C.foo() = "c"
fun D.foo() = "d"
fun printFoo(c: C) {
    println(c.foo()) //扩展函数是静态解析的,不是虚函数(即没有多态)
}

/**
 * 2.类的成员函数和扩展函数-同名同参数:
 * 若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数。
 */
class E {
    fun foo() {
        println("member")
    }
}

fun E.foo() {
    println("extension")
}


/**
 * 3.类的成员函数和扩展函数-同名不同参数:
 */
fun E.foo(i: Int) {
    println("extension")
}


/**
 * 4. 扩展函数内， 可以通过 this 来判断接收者是否为 NULL,这样，即使接收者为 NULL,也可以调用扩展函数。
 */
fun Any?.toString(): String {
    if (this == null) return "$this is null"
    // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
    // 解析为 Any 类的成员函数
    return toString()
}


fun main(args: Array<String>) {
    printFoo(D()) //输出"c",扩展函数调用只取决于参数c的声明类型

    val e = E()
    e.foo() //输出“member”

    val f = E()
    f.foo(2) //输出"extension"

    var t = null
    println(t.toString())
}













