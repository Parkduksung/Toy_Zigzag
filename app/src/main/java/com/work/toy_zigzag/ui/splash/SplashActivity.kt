package com.work.toy_zigzag.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.work.toy_zigzag.R
import com.work.toy_zigzag.base.BaseActivity
import com.work.toy_zigzag.base.ViewState
import com.work.toy_zigzag.databinding.SplashBinding
import com.work.toy_zigzag.ui.shopping.main.ShoppingActivity
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<SplashBinding>(R.layout.splash) {

    private val splashViewModel by inject<SplashViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(splashViewModel)

        initViewModel()
    }


    private fun initViewModel() {
        splashViewModel.viewStateLiveData.observe(this, { viewState: ViewState? ->
            (viewState as? SplashViewModel.SplashViewState)?.let { onChangedViewState(viewState) }
        })
    }

    private fun onChangedViewState(viewState: SplashViewModel.SplashViewState) {
        when (viewState) {
            SplashViewModel.SplashViewState.RouteMain -> startMain()

        }
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