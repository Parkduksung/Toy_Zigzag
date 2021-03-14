package com.work.toy_zigzag.di

import androidx.room.Room
import com.work.toy_zigzag.network.room.database.ShoppingDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val networkModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ShoppingDatabase::class.java,
            "shopping"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}