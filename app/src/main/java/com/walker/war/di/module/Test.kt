package com.walker.war.di.module

import android.content.Context
import com.walker.war.data.ApiHelperImpl
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by admin on 2021/7/10.
 */
// No @Inject because this isn't instantiated in a Dagger context public
class Test {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MyClassInterface {
        fun getFoo(): TestAny

    }

    fun doSomething(context: Context) {
        val myClassInterface =
            EntryPoints.get(context.applicationContext, MyClassInterface::class.java)
        val foo = myClassInterface.getFoo()
    }
}