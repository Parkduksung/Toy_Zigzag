package com.work.toy_zigzag.view.shopping.filter.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.toy_zigzag.R
import com.work.toy_zigzag.databinding.AgeItemBinding
import com.work.toy_zigzag.enums.Sort
import com.work.toy_zigzag.enums.State
import com.work.toy_zigzag.ext.changeBackground
import com.work.toy_zigzag.ext.changeText
import com.work.toy_zigzag.view.shopping.filter.adapter.listener.AdapterListener

class AgeViewHolder(parent: ViewGroup, private val adapterListener: AdapterListener) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.age_item, parent, false
        )
    ) {


    private val binding = AgeItemBinding.bind(itemView)

    fun bind(age: Pair<String, Int>) {

        binding.cbAge.text = age.first

        if (age.second == State.UNCHECK.value) {
            binding.cbAge.apply {
                changeBackground(R.color.colorWhite)
                changeText(R.color.colorPrimary)
                isChecked = false
            }
        } else {
            binding.cbAge.apply {
                changeBackground(R.color.colorPrimary)
                changeText(R.color.colorWhite)
                isChecked = true
            }
        }

        binding.cbAge.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cbAge.apply {
                    changeBackground(R.color.colorPrimary)
                    changeText(R.color.colorWhite)
                }
                adapterListener.getItemState(Sort.AGE, Pair(age.first, State.CHECK.value))
            } else {
                binding.cbAge.apply {
                    changeBackground(R.color.colorWhite)
                    changeText(R.color.colorPrimary)
                }
                adapterListener.getItemState(Sort.AGE, Pair(age.first, State.UNCHECK.value))
            }
        }

    }

}