package com.work.toy_zigzag.view.shopping.main.presenter

import com.work.toy_zigzag.data.model.ShoppingDocumentsItem
import com.work.toy_zigzag.data.model.ShoppingItem

interface ShoppingContract {

    interface View {
        fun showShoppingItem(updateDate: String, list: List<ShoppingDocumentsItem>)
    }

    interface Presenter {
        fun getShoppingItem(shoppingItem: ShoppingItem)
    }

}