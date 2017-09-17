package com.example.jacky.kotlinstudy.basics

/**
 * Created by jacky on 2017/9/17.
 * banker developer. <br/>
 * <br/>
 * {@link http://blog.csdn.net/jjwwmlp456/article/details/74853080}
 */

/**
 *「型变(Variance)」拥有三种基本形态：
 *      协变(Covariant),
 *      逆变(Contravariant),
 *      不变(Nonconviant)
 */

/**
 * 简单的描述 协变与逆变，它们有一个共同的前提，即出现在”有继承或实现”关系的一组类型中。
 *
 * 协变：父类出现的地方，可以用子类代替(符合面向对象的基本原则，里氏替换原则；比如方法返回类型是一个基类型，返回值是一个子类型对象，这就是一种协变)；
 *          父类引用指向子类对象。
 * 逆变：子类出现的地方，可以用父类代替
 *          子类引用指向父类对象。
 *
 * 不变：声明的是什么类型，使用或传递的就要是什么类型
 *
 *
 *
 *      Integer[] iAry = new Integer[]{33};
 *      Object[] objects = iAry;    // 协变
 *      Integer[] iAry2 = objects;  // 逆变
 *
 */

/**
 * 协变 — out — 生产者 — 直接产生与泛型类型同类型的对象
 * 逆变 — in — 消费者 — 用于消费对象或者写入对象
 */






/*
星号类型推断
 */
interface MyFun<in T, out U> {
    fun testp(t: T): U
    fun inParam(t: T)
    fun outValue(): U
}

fun test11(m: MyFun<*, String>) {//in 声明成*, 表示 in Nothing  即不能写入任何东西，因为此时不知道*是什么类型的
//    m.testp()
//    m.inParam()
    m.outValue()
}
fun test12(m: MyFun<Int, *>) {//out 声明成*，表示 out U
    val result = m.testp(33)
    println("test12: " + result)
    m.inParam(34)
    m.outValue()
}
fun test13(m: MyFun<*, *>) {// <in Nothing, out U>
//    m.testp()
//    m.inParam()
    m.outValue()
}