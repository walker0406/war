package com.walker

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by admin on 2021/8/5.
 */
abstract class BaseLifecycleViewHolder(private val itemView: View) :
    RecyclerView.ViewHolder(itemView), LifecycleOwner {
    private var mViewHolderVersion: Int = 0
    private var mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

    init {
        itemView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(v: View?) {
                mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
                mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            }

            override fun onViewAttachedToWindow(v: View?) {
                if (mLifecycleRegistry.currentState != Lifecycle.State.STARTED) {
                    registerLifecycle(false)
                }
            }
        })
    }


    fun registerLifecycle(resetVersion: Boolean) {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        if (resetVersion) {
            mViewHolderVersion++ //被复用后ViewHolder的version加1
        }
        val bindList = bindLiveData(emptyList<Pair<VHLiveData<Any>, Observer<Any>>>())
        bindList?.forEach {
            it.first?.bindLifecycleOwner(this, it.second!! as Observer<in Any?>, resetVersion)
        }
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    abstract fun bindLiveData(list: List<Pair<VHLiveData<out Any>, Observer<out Any>>>): List<Pair<VHLiveData<out Any>, Observer<out Any>>>?


}