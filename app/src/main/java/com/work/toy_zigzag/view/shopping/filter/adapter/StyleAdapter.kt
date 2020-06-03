package com.work.toy_zigzag.view.shopping.filter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.toy_zigzag.enums.ClickState
import com.work.toy_zigzag.view.shopping.filter.adapter.listener.AdapterListener
import com.work.toy_zigzag.view.shopping.filter.adapter.viewholder.StyleViewHolder

class StyleAdapter(private val adapterListener: AdapterListener) :
    RecyclerView.Adapter<StyleViewHolder>() {

    private val styleList = mutableListOf<Pair<String, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StyleViewHolder =
        StyleViewHolder(parent, adapterListener)

    override fun getItemCount(): Int =
        styleList.size

    override fun onBindViewHolder(holder: StyleViewHolder, position: Int) =
        holder.bind(styleList[position])

    fun addAllData(list: List<Pair<String, Int>>) {
        styleList.addAll(list)
    }

    fun clear() {
        styleList.forEachIndexed { index, pair ->
            styleList[index] = Pair(pair.first, ClickState.UNCHECK.value)
        }
        notifyDataSetChanged()
    }
}
