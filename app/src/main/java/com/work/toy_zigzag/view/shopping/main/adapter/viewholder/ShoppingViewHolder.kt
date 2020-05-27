package com.work.toy_zigzag.view.shopping.main.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.work.toy_zigzag.R
import com.work.toy_zigzag.data.model.ShoppingDocumentsItem
import com.work.toy_zigzag.util.Shopping

class ShoppingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.list_item, parent, false
    )
) {
    private val shoppingName = itemView.findViewById<TextView>(R.id.tv_name)
    private val shoppingRank = itemView.findViewById<TextView>(R.id.tv_rank)
    private val shoppingImage = itemView.findViewById<ImageView>(R.id.iv_image)
    private val shoppingAgeGroup = itemView.findViewById<TextView>(R.id.tv_age_group)
    private val shoppingStyle1 = itemView.findViewById<TextView>(R.id.tv_style1)
    private val shoppingStyle2 = itemView.findViewById<TextView>(R.id.tv_style2)

    fun bind(item: ShoppingDocumentsItem, position: Int) {

        shoppingRank.text = (position + 1).toString()

        shoppingName.text = item.name

        shoppingAgeGroup.text = Shopping.getAgeGroup(item.ageGroup)

        Shopping.getStyle(item.sort, shoppingStyle1, shoppingStyle2)

        Glide.with(itemView)
            .load(Shopping.getImage(item.url))
            .circleCrop()
            .error(R.drawable.fail_to_bring_image)
            .into(shoppingImage)
    }

}