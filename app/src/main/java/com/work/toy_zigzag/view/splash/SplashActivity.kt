package com.work.toy_zigzag.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.work.toy_zigzag.App
import com.work.toy_zigzag.R
import com.work.toy_zigzag.data.model.ShoppingItem
import com.work.toy_zigzag.util.Shopping
import com.work.toy_zigzag.view.shopping.main.ShoppingActivity
import com.work.toy_zigzag.view.splash.presenter.SplashContract
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private lateinit var presenter: SplashContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        presenter = get { parametersOf(this) }
        presenter.isExistItem()

    }

    private fun startView(shoppingItem: ShoppingItem) {
        Handler().postDelayed({
            App.prefs.selectFilter = INIT

            Shopping.saveStyleSort(shoppingItem)

            val nextIntent =
                Intent(this, ShoppingActivity::class.java).apply {
                    putExtra(SHOPPING_ITEM, shoppingItem)
                }
            startActivity(nextIntent)
            this@SplashActivity.finish()

        }, DELAY_MILLIS)
    }

    override fun showExistState(shoppingItem: ShoppingItem?) {
        if (shoppingItem != null) {
            startView(shoppingItem)
        } else {
            presenter.registerShopping(FILE_NAME)
        }
    }

    override fun showRegister(shoppingItem: ShoppingItem) {
        startView(shoppingItem)
    }


    companion object {
        private const val DELAY_MILLIS = 1300L
        private const val FILE_NAME = "shop_list.json"
        private const val INIT = ""
        const val SHOPPING_ITEM = "shoppingItem"
    }
}