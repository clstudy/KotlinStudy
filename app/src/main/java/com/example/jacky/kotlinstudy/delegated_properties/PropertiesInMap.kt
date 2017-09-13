package com.example.jacky.kotlinstudy.delegated_properties

/**
 * Created by jacky on 2017/9/13.
 * banker developer. <br/>
 * <br/>
 */

class Animal(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

fun main(args: Array<String>) {
    val user = Animal(mapOf(
            "name" to "John Doe",
            "age"  to 25
    ))

    println("name = ${user.name}, age = ${user.age}")
}