package com.work.toy_zigzag.ui.shopping.main.presenter

import com.work.toy_zigzag.data.model.ShoppingDocumentsItem

interface ShoppingContract {

    interface View {
        fun showShoppingItem(updateDate: String, list: List<ShoppingDocumentsItem>)
    }

    interface Presenter {
        fun getShoppingItem()
    }

}