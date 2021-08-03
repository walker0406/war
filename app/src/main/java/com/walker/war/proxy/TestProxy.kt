package com.walker.war.proxy

import android.util.Log
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Created by admin on 2021/8/3.
 */
class TestProxy {
    fun test11(): TestI {
        return Proxy.newProxyInstance(
            this.javaClass.classLoader,
            arrayOf<Class<*>>(TestI::class.java)
        ) { proxy, method, args ->
            println("start proxy = ")
            method.invoke(object : TestI {
                override fun test() : TestI {
                    println("am the real")
                    return this
                }

                override fun test2(name: String) {
                    println("am the real $proxy")
                }

            }, *args ?: arrayOfNulls<Any>(0))
            println("end proxy")
            return@newProxyInstance proxy
        } as TestI
    }
}

fun main() {
    var objecttest = TestProxy().test11()

}