package com.work.toy_zigzag.di

import com.work.toy_zigzag.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
}