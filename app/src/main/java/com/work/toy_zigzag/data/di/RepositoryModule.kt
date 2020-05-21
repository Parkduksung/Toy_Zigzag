package com.work.toy_zigzag.data.di

import com.work.toy_zigzag.data.repository.ShoppingRepository
import com.work.toy_zigzag.data.repository.ShoppingRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ShoppingRepository> { ShoppingRepositoryImpl(get()) }
}