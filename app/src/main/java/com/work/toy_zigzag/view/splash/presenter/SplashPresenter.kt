package com.work.toy_zigzag.view.splash.presenter

import com.work.toy_zigzag.data.repository.ShoppingRepository

class SplashPresenter(
    private val splashView: SplashContract.View,
    private val shoppingRepository: ShoppingRepository
) : SplashContract.Presenter {

    override fun checkExistItem(fileName: String) {
        shoppingRepository.getAll(
            callback = { shoppingEntity ->
                val toShoppingItem =
                    shoppingEntity.toShoppingItem()
                if (toShoppingItem.list.isNotEmpty()) {
                    splashView.showItem(toShoppingItem)
                } else {
                    registerShopping(fileName)
                }
            }
        )
    }

    override fun registerShopping(fileName: String) {
        shoppingRepository.registerShopping(
            fileName,
            onSuccess = { shoppingEntity ->
                val toShoppingItem =
                    shoppingEntity.toShoppingItem()
                splashView.showItem(toShoppingItem)
            },
            onFailure = {
            })
    }

}