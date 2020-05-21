package com.work.toy_zigzag.data.source.local

import com.work.toy_zigzag.network.room.entity.ShoppingEntity

interface ShoppingLocalDataSource {

    fun registerShopping(
        fileName: String,
        callback: (shoppingEntity: ShoppingEntity?) -> Unit
    )

    fun getAll(
        callback: (shoppingEntity: ShoppingEntity?) -> Unit
    )
}