package com.work.toy_zigzag.ui.shopping.main.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.work.toy_zigzag.R
import com.work.toy_zigzag.data.model.ShoppingDocumentsItem
import com.work.toy_zigzag.databinding.ListItemBinding
import com.work.toy_zigzag.util.Shopping
import com.work.toy_zigzag.ui.shopping.ShoppingListener

class ShoppingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.list_item, parent, false
    )
) {
    private val binding = ListItemBinding.bind(itemView)

    fun bind(
        item: ShoppingDocumentsItem,
        position: Int,
        itemClickListener: ShoppingListener
    ) {

        itemView.setOnClickListener {
            itemClickListener.getItemClick(item)
        }

        binding.apply {
            tvRank.text = (position + 1).toString()
            tvName.text = item.name
            tvAgeGroup.text = Shopping.getAgeGroup(item.ageGroup)
        }

        Shopping.getStyle(item.sort, binding.tvStyle1, binding.tvStyle2)

        Glide.with(itemView)
            .load(Shopping.getImage(item.url))
            .circleCrop()
            .error(R.drawable.fail_to_bring_image)
            .into(binding.ivImage)
    }

}