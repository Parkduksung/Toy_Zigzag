package com.work.toy_zigzag.view.shopping.filter.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.toy_zigzag.R
import com.work.toy_zigzag.databinding.StyleItemBinding
import com.work.toy_zigzag.enums.Sort
import com.work.toy_zigzag.enums.State
import com.work.toy_zigzag.ext.changeBackground
import com.work.toy_zigzag.ext.changeText
import com.work.toy_zigzag.view.shopping.filter.adapter.listener.AdapterListener

class StyleViewHolder(parent: ViewGroup, private val adapterListener: AdapterListener) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.style_item, parent, false
        )
    ) {

    private val binding = StyleItemBinding.bind(itemView)

    fun bind(style: Pair<String, Int>) {

        binding.cbStyle.text = style.first

        if (style.second == State.UNCHECK.value) {
            binding.cbStyle.apply {
                changeBackground(R.color.colorWhite)
                changeText(R.color.colorAccent)
                isChecked = false
            }
        } else {
            binding.cbStyle.apply {
                changeBackground(R.color.colorAccent)
                changeText(R.color.colorWhite)
                isChecked = true
            }
        }

        binding.cbStyle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cbStyle.apply {
                    changeBackground(R.color.colorAccent)
                    changeText(R.color.colorWhite)
                }
                adapterListener.getItemState(Sort.STYLE, Pair(style.first, State.CHECK.value))
            } else {
                binding.cbStyle.apply {
                    changeBackground(R.color.colorWhite)
                    changeText(R.color.colorAccent)
                }
                adapterListener.getItemState(Sort.STYLE, Pair(style.first, State.UNCHECK.value))
            }
        }
    }

}