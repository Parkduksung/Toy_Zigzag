package com.work.toy_zigzag.ui.shopping.filter.adapter.listener

import com.work.toy_zigzag.enums.Sort

interface AdapterListener {
    fun getItemState(
        sort: Sort,
        changeItem: Pair<String, Int>
    )
}