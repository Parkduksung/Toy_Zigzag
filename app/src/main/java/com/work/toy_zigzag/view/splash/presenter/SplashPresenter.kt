package com.work.toy_zigzag.view.splash.presenter

import com.work.toy_zigzag.data.repository.ShoppingRepository

class SplashPresenter(
    private val splashView: SplashContract.View,
    private val shoppingRepository: ShoppingRepository
) : SplashContract.Presenter {

    override fun registerShopping(fileName: String) {
        shoppingRepository.registerShopping(
            fileName,
            callback = { shoppingEntity ->
                shoppingEntity?.let {
                    val toShoppingItem =
                        shoppingEntity.toShoppingItem()
                    splashView.showRegister(toShoppingItem)
                }
            }
        )
    }

    override fun isExistItem() {
        shoppingRepository.getAll(
            callback = { shoppingEntity ->
                val toShoppingItem =
                    shoppingEntity?.toShoppingItem()
                splashView.showExistState(toShoppingItem)
            }
        )
    }
}