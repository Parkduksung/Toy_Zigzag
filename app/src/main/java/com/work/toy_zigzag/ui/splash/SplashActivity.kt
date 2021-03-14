package com.work.toy_zigzag.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.work.toy_zigzag.R
import com.work.toy_zigzag.base.BaseActivity
import com.work.toy_zigzag.databinding.SplashBinding
import com.work.toy_zigzag.ui.shopping.main.ShoppingActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashBinding>(R.layout.splash) {

    private val splashViewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(splashViewModel)

        splashViewModel.onEventLiveData.observe(this, { event ->
            when (event) {
                SplashViewModel.OnEvent.RouteMain -> {
                    startMain()
                }
                else -> {
                }
            }
        })
    }


    private fun startMain() {
        Handler().postDelayed({
            startActivity(
                Intent(this, ShoppingActivity::class.java)
            )
            finish()
        }, DELAY_MILLIS)
    }

    companion object {
        private const val DELAY_MILLIS = 1300L
    }
}