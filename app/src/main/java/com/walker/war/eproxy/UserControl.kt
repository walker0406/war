package com.walker.war.eproxy

import android.util.Log
import android.view.View
import android.widget.Toast
import com.airbnb.epoxy.TypedEpoxyController
import com.walker.war.ItemLayoutBindingModel_
import com.walker.war.data.model.User
import com.walker.war.itemLayout

/**
 * Created by admin on 2021/7/16.
 */

class UserControl : TypedEpoxyController<List<User>>() {


    override fun buildModels(user: List<User>) {
        user.forEach {
            var user = it
            ItemLayoutBindingModel_().apply {
                this.name(it.name)
                this.email(it.email)
                this.id(it.id)
                this.onClick(View.OnClickListener {
                    Log.d("test","===="+user.name)
                })
                this.addTo(this@UserControl)
//                email = it.email
            }
//            ItemModel_().apply {
//                name = it.name
//                email = it.email
//                id(it.id)
//                addTo(this@UserControl)
//            }

        }
    }

}
