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

class StyleViewHolder(parent: ViewGroup, private val adapterListener: AdapterListener) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.style_item, parent, false
        )
    ) {
    private val styleButton =
        itemView.findViewById<CheckBox>(R.id.cb_style)

    fun bind(style: Pair<String, Int>) {
        styleButton.text = style.first

        if (style.second == State.UNCHECK.value) {
            styleButton.apply {
                changeBackground(this, R.color.colorWhite)
                changeText(this, R.color.colorAccent)
            }
        }

        styleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                styleButton.apply {
                    changeBackground(this, R.color.colorAccent)
                    changeText(this, R.color.colorWhite)
                }
                adapterListener.getItemState(Sort.STYLE, Pair(style.first, State.CHECK.value))
            } else {
                styleButton.apply {
                    changeBackground(this, R.color.colorWhite)
                    changeText(this, R.color.colorAccent)
                }
                adapterListener.getItemState(Sort.STYLE, Pair(style.first, State.UNCHECK.value))
            }
        }
    }

}