package com.work.toy_zigzag.view.shopping.filter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.toy_zigzag.enums.State
import com.work.toy_zigzag.view.shopping.filter.adapter.listener.AdapterListener
import com.work.toy_zigzag.view.shopping.filter.adapter.viewholder.AgeViewHolder

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
        ageList.let { ageList.map { Pair(it.first, State.UNCHECK.value) } }
        notifyDataSetChanged()
    }
}