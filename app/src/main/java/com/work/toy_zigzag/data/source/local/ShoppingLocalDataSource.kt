package com.work.toy_zigzag.data.source.local

import com.work.toy_zigzag.network.room.entity.ShoppingEntity

interface ShoppingLocalDataSource {

    fun registerShopping(
        fileName: String,
        onSuccess: (shoppingEntity: ShoppingEntity) -> Unit,
        onFailure: () -> Unit
    )

    fun getAll(
        callback: (shoppingEntity: ShoppingEntity) -> Unit
    )
}