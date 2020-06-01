package com.work.toy_zigzag.data.repository

import com.work.toy_zigzag.network.room.entity.ShoppingEntity

interface ShoppingRepository {

    fun registerShopping(
        fileName: String,
        onSuccess: (shoppingEntity: ShoppingEntity) -> Unit,
        onFailure: () -> Unit
    )

    fun getAll(
        callback: (shoppingEntity: ShoppingEntity) -> Unit
    )
}