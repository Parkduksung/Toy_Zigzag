package com.work.toy_zigzag.view.shopping

import com.work.toy_zigzag.data.model.ShoppingItem

interface ShoppingListener {
    fun getSelectData(shoppingItem: ShoppingItem)
}