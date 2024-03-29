package com.work.toy_zigzag.ui.shopping.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.toy_zigzag.data.model.ShoppingDocumentsItem
import com.work.toy_zigzag.ui.shopping.ShoppingListener
import com.work.toy_zigzag.ui.shopping.main.adapter.viewholder.ShoppingViewHolder

class ShoppingAdapter : RecyclerView.Adapter<ShoppingViewHolder>() {

    private val shoppingList = mutableListOf<ShoppingDocumentsItem>()

    private lateinit var shoppingListener: ShoppingListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder =
        ShoppingViewHolder(parent)

    override fun getItemCount(): Int =
        shoppingList.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) =
        holder.bind(shoppingList[position], position, shoppingListener)

    fun addData(item: ShoppingDocumentsItem) {
        shoppingList.add(item)
        notifyItemInserted(shoppingList.lastIndex)
    }

    fun clear() {
        shoppingList.clear()
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ShoppingListener) {
        shoppingListener = listener
    }
}