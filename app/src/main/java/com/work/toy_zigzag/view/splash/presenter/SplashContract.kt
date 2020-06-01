package com.work.toy_zigzag.view.splash.presenter

import com.work.toy_zigzag.data.model.ShoppingItem

interface SplashContract {

    interface View {
        fun showItem(shoppingItem: ShoppingItem)
    }

    interface Presenter {
        fun checkExistItem(fileName: String)
    }
}