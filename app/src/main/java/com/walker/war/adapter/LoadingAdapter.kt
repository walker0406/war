package com.walker.war.adapter

import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.walker.war.R
import com.walker.war.databinding.LoadingLayoutBinding

/**
 * Created by admin on 2021/7/20.
 */
class LoadingAdapter : LoadStateAdapter<LoadingAdapter.LoadStatusHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStatusHolder {
        return LoadStatusHolder(
            DataBindingUtil.inflate(
                parent.context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                R.layout.loading_layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LoadStatusHolder, loadState: LoadState) {
        holder?.bind(loadState)
    }

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        Log.d("guowtest", "LoadState=$loadState")
        return loadState is LoadState.Loading || loadState is LoadState.Error || loadState is LoadState.NotLoading
    }


    class LoadStatusHolder(var binding: LoadingLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            when (loadState) {
                is LoadState.NotLoading -> binding.tvLoading.text = "NotLoading"
                is LoadState.Loading -> binding.tvLoading.text = "loading"
                is LoadState.Error -> binding.tvLoading.text = "error"
            }

        }
    }
}