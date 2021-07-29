package com.walker.war

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.walker.war.adapter.PageAdapter

/**
 * Created by admin on 2021/7/27.
 */

class SelectLoopUp(private val rv: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent)
            : ItemDetails<Long>? {
        val view = rv.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (rv.getChildViewHolder(view) as PageAdapter.PageViewHolder)
                .getItemDetails()
        }
        return null

    }
}