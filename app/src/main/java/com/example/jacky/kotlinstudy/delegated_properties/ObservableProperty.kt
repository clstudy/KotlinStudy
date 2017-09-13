package com.example.jacky.kotlinstudy.delegated_properties

import kotlin.properties.Delegates

/**
 * Created by jacky on 2017/9/13.
 * banker developer. <br/>
 * <br/>
 */

class User {
    var name: String by Delegates.observable("no name") {
        d, old, new ->
        println("$old - $new")
    }
}

fun main(args: Array<String>) {
    val user = User()
    user.name = "Carl"
}