package com.work.toy_zigzag.view.shopping.filter.presenter

import com.work.toy_zigzag.data.model.ShoppingItem

interface ShoppingFilterContract {

    interface View {
        fun showItem(shoppingItem: ShoppingItem)

        fun showSelectFilterList(
            sort : Int,
            itemMapList: List<Pair<String, Int>>,
            isCheckItemMap: Map<String, Int>
        )
    }

    interface Presenter {
        fun getItem(ageGroup: List<Int>, styleList: List<String>)

        fun checkSelectFilter(list: List<String>)
    }
}