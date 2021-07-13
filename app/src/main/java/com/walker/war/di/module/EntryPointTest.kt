package com.walker.war.di.module

import android.content.Context
import android.util.Log
import com.walker.war.data.ApiHelperImpl
import com.walker.war.di.qualifier.Test2
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

/**
 * Created by admin on 2021/7/10.
 */
// No @Inject because this isn't instantiated in a Dagger context public
class EntryPointTest {

    fun doSomething(context: Context?) {
        val myClassInterface =
            EntryPoints.get(context?.applicationContext, ApplicationModule.MyClassInterface::class.java)
        val foo = myClassInterface.getFoo()
        Log.d("guowtest", "foo="+foo.hashCode())
    }
}