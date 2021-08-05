package com.walker.war.adapter

import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sankuai.waimai.router.Router
import com.sankuai.waimai.router.common.DefaultUriRequest
import com.walker.BaseLifeCycleAdapter
import com.walker.BaseLifecycleViewHolder
import com.walker.VHLiveData
import com.walker.war.R
import com.walker.war.data.model.User
import com.walker.war.databinding.ItemLayoutBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull
import timber.log.Timber
import kotlin.random.Random


/**
 * Created by admin on 2021/7/20.
 */
class PageAdapter :
    BaseLifeCycleAdapter<User, PageAdapter.PageViewHolder>(object : ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.equals(newItem)
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageAdapter.PageViewHolder {

        return PageViewHolder(
            DataBindingUtil.inflate(
                parent.context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                R.layout.item_layout, parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {

        val user = getItem(position)
        user?.apply {
            holder.bind(user)
        }
        holder.binding.onClick = View.OnClickListener {
            // Router.startUri(holder.binding.root.context, "/account")
            DefaultUriRequest(holder.binding.root.context, "/account").putExtra("test", 1).start()
        }
        holder.binding.executePendingBindings()
        super.onBindViewHolder(holder, position)

//        if (tracker!!.isSelected(user.id.toLong())) {
//            holder.itemView.isActivated = true
//            holder.binding.root.background = ColorDrawable(Color.parseColor("#80deea"))
//        } else {
//            // Reset color to white if not selected
//            holder.binding.root.background = ColorDrawable(Color.WHITE)
//            holder.itemView.isActivated = true
//        }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }


    class PageViewHolder(var binding: ItemLayoutBinding) : BaseLifecycleViewHolder(binding.root) {
        var user: User? = null
        fun bind(user: User) {
            this.user = user
            binding.root.tag = user
            Thread {
                val randoms = (0..2000).random()
                Timber.d("======" + randoms)
                Thread.sleep(randoms.toLong())
                Handler(Looper.getMainLooper()).post {
                    binding.apply {
                        if (binding.root.tag == user) {
                            this.name = user?.name
                            this.image = user?.avatar
                            this.email = user?.email
                        }
                    }
                }
            }.start()
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int {
                    return bindingAdapterPosition
                }

                override fun getSelectionKey(): Long? {
                    return itemId
                }

                //单击需重写，否则longpress 才生效
                override fun inSelectionHotspot(s: MotionEvent): Boolean = true

            }


        override fun bindLiveData(list: List<Pair<VHLiveData<out Any>, Observer<out Any>>>): List<Pair<VHLiveData<out Any>, Observer<out Any>>>? {
            var liveData: VHLiveData<User> = VHLiveData()
            Thread {
                val randoms = (0..1000).random()
                Thread.sleep(randoms.toLong())
                liveData.postValue(user)
            }.start()
            var listreslult = list.plus(Pair(first = liveData, second = Observer<User?> {
                binding.apply {
                    this.name = it?.name
                    this.image = it?.avatar
                    this.email = it?.email
                }
            }))
            return list
        }

    }

    private var tracker: SelectionTracker<Long>? = null

    fun setTracker(tracker: SelectionTracker<Long>?) {
        this.tracker = tracker
    }
}
