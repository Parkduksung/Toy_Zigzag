package com.work.toy_zigzag.view.shopping.filter.presenter

import com.work.toy_zigzag.data.model.ShoppingItem

interface ShoppingFilterContract {

    interface View {
        fun showItem(shoppingItem: ShoppingItem)
    }

    interface Presenter {
        fun getItem(ageGroup: List<Int>, styleList: List<String>)
    }
}