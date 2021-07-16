package com.walker.war.eproxy

import android.util.Log
import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.walker.war.ItemLayoutBindingModel_
import com.walker.war.data.model.User

/**
 * Created by admin on 2021/7/16.
 */
class UserControlPage : PagingDataEpoxyController<User>() {
    override fun buildItemModel(currentPosition: Int, item: User?): EpoxyModel<*> {
        return ItemLayoutBindingModel_().apply {
            this.name(item?.name)
            this.email(item?.email)
            this.id(item?.id)
            this.image(item?.avatar)
            this.onClick(View.OnClickListener {
                Log.d("test", "====" + item?.name)
            })
           // this.addTo(this@UserControlPage)
//                email = it.email
        }

    }

}