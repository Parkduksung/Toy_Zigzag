package com.work.toy_zigzag.view.shopping.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.toy_zigzag.data.model.ShoppingDocumentsItem
import com.work.toy_zigzag.view.shopping.main.adapter.viewholder.ShoppingViewHolder

class ShoppingAdapter : RecyclerView.Adapter<ShoppingViewHolder>() {

    private val shoppingList = mutableListOf<ShoppingDocumentsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder =
        ShoppingViewHolder(parent)

    override fun getItemCount(): Int =
        shoppingList.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) =
        holder.bind(shoppingList[position], position)

    fun addData(item: ShoppingDocumentsItem) {
        shoppingList.add(item)
        notifyItemInserted(shoppingList.lastIndex)
    }

    fun clear() {
        shoppingList.clear()
        notifyDataSetChanged()
    }

}