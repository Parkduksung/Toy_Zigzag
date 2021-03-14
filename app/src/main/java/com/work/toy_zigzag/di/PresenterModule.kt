package com.work.toy_zigzag.di

import com.work.toy_zigzag.ui.shopping.filter.presenter.ShoppingFilterContract
import com.work.toy_zigzag.ui.shopping.filter.presenter.ShoppingFilterPresenter
import com.work.toy_zigzag.ui.shopping.main.presenter.ShoppingContract
import com.work.toy_zigzag.ui.shopping.main.presenter.ShoppingPresenter
import org.koin.dsl.module


val presenterModule = module {
    factory<ShoppingFilterContract.Presenter> { (view: ShoppingFilterContract.View) ->
        ShoppingFilterPresenter(
            view,
            get()
        )
    }

    factory<ShoppingContract.Presenter> { (view: ShoppingContract.View) ->
        ShoppingPresenter(
            view,
            get()
        )
    }
}