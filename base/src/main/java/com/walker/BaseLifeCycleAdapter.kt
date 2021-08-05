package com.walker

import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * Created by admin on 2021/8/5.
 */
abstract class BaseLifeCycleAdapter<T : Any?, VH : BaseLifecycleViewHolder>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diffCallback) {

    override fun onBindViewHolder(@NonNull holder: VH, position: Int) {
        holder.registerLifecycle(true)
    }

}