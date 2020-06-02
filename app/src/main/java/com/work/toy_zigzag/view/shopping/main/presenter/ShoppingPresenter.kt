package com.work.toy_zigzag.view.shopping.main.presenter

import com.work.toy_zigzag.App
import com.work.toy_zigzag.data.repository.ShoppingRepository
import com.work.toy_zigzag.util.Shopping

class ShoppingPresenter(
    private val shoppingView: ShoppingContract.View,
    private val shoppingRepository: ShoppingRepository
) : ShoppingContract.Presenter {

    override fun getShoppingItem() {

        shoppingRepository.getAll {
            val toShoppingItem =
                it.toShoppingItem()

            val toSelectItem =
                Shopping.getSelectFilterList(toShoppingItem, App.prefs.selectFilter)

            shoppingView.showShoppingItem(toSelectItem.week, toSelectItem.list)

        }

    }
}
