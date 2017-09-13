package com.example.jacky.kotlinstudy.basics

/**
 * Created by jacky on 2017/9/13.
 * banker developer. <br/>
 * <br/>
 */

fun main(args: Array<String>) {

    //类型后面加?表示可为空
    var agen: String? = "22"
    //抛出空指针异常
    val ages = agen!!.toInt()
    //不做处理返回 null
    val ages1 = agen?.toInt()
    //age为空返回-1
    var age: String? = null
    val ages2 = age?.toInt() ?: -1
    println(ages2)

}