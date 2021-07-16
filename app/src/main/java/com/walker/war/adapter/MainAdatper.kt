package com.walker.war.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyDataBindingLayouts
import com.bumptech.glide.Glide
import com.walker.war.R
import com.walker.war.data.model.User
import com.walker.war.databinding.ItemLayoutBinding

/**
 * Created by admin on 2021/7/10.
 */

class MainAdapter(

) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    private val users: ArrayList<User> = ArrayList()
    fun updateData(data: ArrayList<User>?) {
        data?.let {
            users.clear()
            users.addAll(it)
            notifyDataSetChanged()
        }

    }

    class DataViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.name = user.name;
            binding.email = user.email
//            binding.textViewUserName.text = user.name
//            binding.textViewUserEmail.text = user.email
            Glide.with(binding.imageViewAvatar.context)
                .load(user.avatar)
                .into(binding.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_layout, parent,
                false
            )

        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}