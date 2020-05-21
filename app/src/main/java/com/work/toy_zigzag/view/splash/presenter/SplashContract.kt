package com.work.toy_zigzag.view.splash.presenter

import com.work.toy_zigzag.data.model.ShoppingItem

interface SplashContract {

    interface View {

        fun showRegister(shoppingItem: ShoppingItem)

        fun showExistState(shoppingItem: ShoppingItem?)
    }

    interface Presenter {

        fun registerShopping(fileName: String)

        fun isExistItem()
    }
}