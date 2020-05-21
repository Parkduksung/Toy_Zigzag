package com.work.toy_zigzag.view.shopping.filter.presenter

import com.work.toy_zigzag.data.repository.ShoppingRepository
import com.work.toy_zigzag.util.Shopping

class ShoppingFilterPresenter(
    private val shoppingFilterView: ShoppingFilterContract.View,
    private val shoppingRepository: ShoppingRepository
) : ShoppingFilterContract.Presenter {

    override fun getItem(ageGroup: List<Int>, styleList: List<String>) {
        shoppingRepository.getAll(
            callback = { shoppingEntity ->
                val toShoppingItem =
                    shoppingEntity?.toShoppingItem()

                toShoppingItem?.let { shoppingItem ->
                    shoppingFilterView.showItem(
                        Shopping.getCheckShoppingItem(
                            shoppingItem,
                            ageGroup,
                            styleList
                        )
                    )
                }
            })
    }

}