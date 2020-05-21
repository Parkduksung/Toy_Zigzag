package com.work.toy_zigzag.view.shopping.main.presenter

import com.work.toy_zigzag.data.model.ShoppingItem

class ShoppingPresenter(private val shoppingView: ShoppingContract.View) :
    ShoppingContract.Presenter {
    override fun getShoppingItem(shoppingItem: ShoppingItem) {
        shoppingView.showShoppingItem(
            shoppingItem.week,
            shoppingItem.list.sortedByDescending { it.likeCount }
        )
    }
}
