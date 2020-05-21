package com.work.toy_zigzag.data.di

import com.work.toy_zigzag.data.source.local.ShoppingLocalDataSource
import com.work.toy_zigzag.data.source.local.ShoppingLocalDataSourceImpl
import org.koin.dsl.module

val sourceModule = module {
    single<ShoppingLocalDataSource> { ShoppingLocalDataSourceImpl(get(), get()) }
}