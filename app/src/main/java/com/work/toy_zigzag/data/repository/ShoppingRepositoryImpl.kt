package com.work.toy_zigzag.data.repository

import com.work.toy_zigzag.data.source.local.ShoppingLocalDataSource
import com.work.toy_zigzag.network.room.entity.ShoppingEntity

class ShoppingRepositoryImpl(private val shoppingLocalDataSource: ShoppingLocalDataSource) :
    ShoppingRepository {
    override fun getAll(callback: (shoppingEntity: ShoppingEntity?) -> Unit) {
        shoppingLocalDataSource.getAll(callback)
    }

    override fun registerShopping(
        fileName: String,
        callback: (shoppingEntity: ShoppingEntity?) -> Unit
    ) {
        shoppingLocalDataSource.registerShopping(fileName, callback)
    }

}