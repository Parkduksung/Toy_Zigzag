package com.work.toy_zigzag.view.shopping.filter.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.work.toy_zigzag.R
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
    private val ageCheckBox =
        itemView.findViewById<CheckBox>(R.id.cb_age)

    fun bind(age: Pair<String, Int>) {
        ageCheckBox.text = age.first

        if (age.second == State.UNCHECK.value) {
            ageCheckBox.apply {
                changeBackground(this, R.color.colorWhite)
                changeText(this, R.color.colorPrimary)
            }
        }

        ageCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ageCheckBox.apply {
                    changeBackground(this, R.color.colorPrimary)
                    changeText(this, R.color.colorWhite)
                }
                adapterListener.getItemState(Sort.AGE, Pair(age.first, State.CHECK.value))
            } else {
                ageCheckBox.apply {
                    changeBackground(this, R.color.colorWhite)
                    changeText(this, R.color.colorPrimary)
                }
                adapterListener.getItemState(Sort.AGE, Pair(age.first, State.UNCHECK.value))
            }
        }

    }

}