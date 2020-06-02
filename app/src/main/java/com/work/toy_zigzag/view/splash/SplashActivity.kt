package com.work.toy_zigzag.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.work.toy_zigzag.R
import com.work.toy_zigzag.data.model.ShoppingItem
import com.work.toy_zigzag.databinding.SplashBinding
import com.work.toy_zigzag.util.Shopping
import com.work.toy_zigzag.view.shopping.main.ShoppingActivity
import com.work.toy_zigzag.view.splash.presenter.SplashContract
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private lateinit var presenter: SplashContract.Presenter

    private lateinit var binding: SplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.splash
            )

        presenter = get { parametersOf(this) }
        presenter.checkExistItem(FILE_NAME)

    }

    override fun showItem(shoppingItem: ShoppingItem) {
        startView(shoppingItem)
    }

    private fun startView(shoppingItem: ShoppingItem) {
        Handler().postDelayed({

            Shopping.saveStyleSort(shoppingItem)

            startActivity(
                Intent(this, ShoppingActivity::class.java)
            )
            finish()
        }, DELAY_MILLIS)
    }

    companion object {
        private const val DELAY_MILLIS = 1300L
        private const val FILE_NAME = "shop_list.json"
    }
}