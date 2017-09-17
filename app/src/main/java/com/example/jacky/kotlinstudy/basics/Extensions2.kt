package com.example.jacky.kotlinstudy.basics

/**
 * Created by jacky on 2017/9/17.
 * banker developer. <br/>
 * <br/>
 */
//data class Const2(val number: Double) : Expr()  //报错

open class K

class K1 : K()

open class O {
    open fun K.foo() {
        println("K.foo in O")
    }

    open fun K1.foo() {
        println("K1.foo in O")
    }

    fun call(k: K) {
        k.foo()   // 调用扩展函数
    }
}

class O1 : O() {
    override fun K.foo() {
        println("K.foo in O1")
    }

    override fun K1.foo() {
        println("K1.foo in O1")
    }
}


fun main(args: Array<String>) {
    O().call(K())   // 输出 "K.foo in O"
    O().call(K1())  // 输出 "K.foo in O", 扩展接收者静态解析(非多态)
    O1().call(K())  // 输出 "K.foo in O1",分发接收者虚拟解析(多态)

}



