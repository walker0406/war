package com.walker.war.eproxy

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.walker.war.R
import com.walker.war.R2


/**
 * Created by admin on 2021/7/16.
 */
@EpoxyModelClass(layout = R.layout.item_layout)
abstract class ItemModel : EpoxyModelWithHolder<ItemModel.Holder?>() {
    // Declare your model properties like this
    @EpoxyAttribute
    var name = ""

    @EpoxyAttribute
    var email = ""

//    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
//    var clickListener: View.OnClickListener? = null
    override fun bind(holder: Holder) {
        // Implement this to bind the properties to the view
        holder.name?.text = name
        //holder.email.setOnClickListener(clickListener)
        holder.email?.text = email
    }

    class Holder : EpoxyHolder() {
        var name: TextView? = null
        var email: TextView? = null
        override fun bindView(itemView: View) {
            name = itemView.findViewById(R.id.textViewUserName)
            email = itemView.findViewById(R.id.textViewUserEmail)
        }
    }
}