package com.work.toy_zigzag.ui.shopping.filter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.toy_zigzag.enums.ClickState
import com.work.toy_zigzag.ui.shopping.filter.adapter.listener.AdapterListener
import com.work.toy_zigzag.ui.shopping.filter.adapter.viewholder.AgeViewHolder

class AgeAdapter(private val adapterListener: AdapterListener) :
    RecyclerView.Adapter<AgeViewHolder>() {

    private val ageList = mutableListOf<Pair<String, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgeViewHolder =
        AgeViewHolder(parent, adapterListener)

    override fun getItemCount(): Int =
        ageList.size

    override fun onBindViewHolder(holder: AgeViewHolder, position: Int) =
        holder.bind(ageList[position])

    fun addAllData(list: List<Pair<String, Int>>) {
        ageList.addAll(list)
    }

    fun clear() {
        ageList.forEachIndexed { index, pair ->
            ageList[index] = Pair(pair.first, ClickState.UNCHECK.value)
        }
        notifyDataSetChanged()
    }
}