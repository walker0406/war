package com.walker

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData

import androidx.lifecycle.LifecycleOwner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.lang.Exception
import java.lang.reflect.Field
import java.lang.reflect.Method


/**
 * Created by admin on 2021/8/5.
 */
class VHLiveData<T> : MutableLiveData<T?>() {

    fun bindLifecycleOwner(
        @NonNull owner: LifecycleOwner?,
        @NonNull observer: Observer<in T?>,
        resetVersion: Boolean
    ) {
        super.observe(owner!!, observer)
        if (resetVersion) {
            try {
                val hySuperClass: Class<*> = LiveData::class.java
                val observers: Field = hySuperClass.getDeclaredField("mObservers")
                observers.isAccessible = true
                val objectObservers: Any = observers.get(this)
                val classObservers: Class<*> = objectObservers.javaClass
                val methodGet: Method = classObservers.getDeclaredMethod("get", Any::class.java)
                methodGet.isAccessible = true
                val objectWrapperEntry: Any = methodGet.invoke(objectObservers, observer)
                var objectWrapper: Any? = null
                if (objectWrapperEntry is Map.Entry<*, *>) {
                    objectWrapper = objectWrapperEntry.value
                }
                if (objectWrapper != null) {
                    val classObserverWrapper: Class<*> = objectWrapper.javaClass.superclass
                    val lastVersion: Field = classObserverWrapper.getDeclaredField("mLastVersion")
                    lastVersion.isAccessible = true
                    val version: Field = hySuperClass.getDeclaredField("mVersion")
                    version.isAccessible = true
                    val objectVersion: Any = version.get(this) //set wrapper's version
                    lastVersion.set(objectWrapper, objectVersion)
                    Timber.d("set mLastVersion: $objectVersion")
                }
            } catch (e: Exception) {
                Timber.d("set mLastVersion failed")
                e.printStackTrace()
            }
        }
    }
}