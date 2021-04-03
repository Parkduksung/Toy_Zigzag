package com.work.toy_zigzag.data.repository

import com.work.toy_zigzag.data.source.local.ShoppingLocalDataSource
import com.work.toy_zigzag.network.room.entity.ShoppingEntity

class ShoppingRepositoryImpl(private val shoppingLocalDataSource: ShoppingLocalDataSource) :
    ShoppingRepository {
    override fun getAll(onSuccess: (shoppingEntity: ShoppingEntity) -> Unit, onFailure: () -> Unit) {
        shoppingLocalDataSource.getAll(onSuccess, onFailure)
    }

    override fun registerShopping(
        fileName: String,
        onSuccess: (shoppingEntity: ShoppingEntity) -> Unit,
        onFailure: () -> Unit
    ) {
        shoppingLocalDataSource.registerShopping(fileName, onSuccess, onFailure)
    }

}