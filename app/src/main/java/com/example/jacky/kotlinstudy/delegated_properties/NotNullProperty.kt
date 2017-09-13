package com.example.jacky.kotlinstudy.delegated_properties

import kotlin.properties.Delegates

/**
 * Created by jacky on 2017/9/13.
 * banker developer. <br/>
 * <br/>
 */

class People {
    var name: String by Delegates.notNull()

    fun init(name: String) {
        this.name = name
    }
}

fun main(args: Array<String>) {
    val user = People()
    // user.name -> IllegalStateException
    user.init("Carl")
    println(user.name)
}