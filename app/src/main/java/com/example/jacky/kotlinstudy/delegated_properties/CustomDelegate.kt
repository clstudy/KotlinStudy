package com.example.jacky.kotlinstudy.delegated_properties

import kotlin.reflect.KProperty

/**
 * Created by jacky on 2017/9/13.
 * banker developer. <br/>
 * <br/>
 */

class Example {
    var p: String by Delegate()

    override fun toString() = "Example Class"
}

class Delegate() {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${prop.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        println("$value has been assigned to ${prop.name} in $thisRef")
    }
}

fun main(args: Array<String>) {
    val e = Example()
    println(e.p)
    e.p = "NEW"
}
