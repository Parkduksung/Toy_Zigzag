package com.work.toy_zigzag.view.shopping

import com.work.toy_zigzag.data.model.ShoppingDocumentsItem
import com.work.toy_zigzag.data.model.ShoppingItem

interface ShoppingListener {
    fun getSelectData(shoppingItem: ShoppingItem)
    fun getItemClick(shoppingDocumentsItem: ShoppingDocumentsItem)
}