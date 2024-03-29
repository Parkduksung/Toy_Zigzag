package com.work.toy_zigzag.data.repository

import com.work.toy_zigzag.network.room.entity.ShoppingEntity
import com.work.toy_zigzag.util.Result

interface ShoppingRepository {

    fun registerShopping(
        fileName: String,
        onSuccess: (shoppingEntity: ShoppingEntity) -> Unit,
        onFailure: () -> Unit
    )

    fun getAll(
        onSuccess: (shoppingEntity: ShoppingEntity) -> Unit,
        onFailure: () -> Unit
    )

    suspend fun isExistShoppingData() : Boolean

    suspend fun registerShoppingData(fileName: String) : Result<ShoppingEntity>

}