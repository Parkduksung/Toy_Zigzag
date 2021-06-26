package com.work.toy_zigzag.di

import com.work.toy_zigzag.ui.splash.SplashInteractor
import org.koin.dsl.module

val interactorModule = module {
    factory { SplashInteractor() }
}