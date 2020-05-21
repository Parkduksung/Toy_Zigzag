package com.work.toy_zigzag.view.shopping.filter.adapter.listener

import com.work.toy_zigzag.enums.Sort

interface AdapterListener {
    fun getItemState(sort: Sort, state: Pair<String, Int>)
}