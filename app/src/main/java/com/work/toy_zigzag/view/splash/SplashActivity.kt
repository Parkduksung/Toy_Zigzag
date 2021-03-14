package com.work.toy_zigzag.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.work.toy_zigzag.R
import com.work.toy_zigzag.base.BaseActivity
import com.work.toy_zigzag.data.model.ShoppingItem
import com.work.toy_zigzag.databinding.SplashBinding
import com.work.toy_zigzag.util.Shopping
import com.work.toy_zigzag.view.shopping.main.ShoppingActivity
import com.work.toy_zigzag.view.splash.presenter.SplashContract
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SplashActivity : BaseActivity<SplashBinding>(R.layout.splash), SplashContract.View {

    private lateinit var presenter: SplashContract.Presenter

    private val splashViewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = get { parametersOf(this) }
        presenter.registerShopping(FILE_NAME)
    }

    override fun showItem(shoppingItem: ShoppingItem) {
        startView(shoppingItem)
    }

    private fun startView(shoppingItem: ShoppingItem) {
        Handler().postDelayed({

            Shopping.saveStyleSort(shoppingItem)

            startMain()
        }, DELAY_MILLIS)
    }

    private fun startMain() {
        startActivity(
            Intent(this, ShoppingActivity::class.java)
        )
        finish()
    }

    companion object {
        private const val DELAY_MILLIS = 1300L
        private const val FILE_NAME = "shop_list.json"
    }
}