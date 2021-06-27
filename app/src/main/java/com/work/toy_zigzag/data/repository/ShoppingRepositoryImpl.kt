package com.work.toy_zigzag.data.repository

import com.work.toy_zigzag.data.source.local.ShoppingLocalDataSource
import com.work.toy_zigzag.network.room.entity.ShoppingEntity
import com.work.toy_zigzag.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoppingRepositoryImpl(private val shoppingLocalDataSource: ShoppingLocalDataSource) :
    ShoppingRepository {
    override fun getAll(
        onSuccess: (shoppingEntity: ShoppingEntity) -> Unit,
        onFailure: () -> Unit
    ) {
        shoppingLocalDataSource.getAll(onSuccess, onFailure)
    }

    override fun registerShopping(
        fileName: String,
        onSuccess: (shoppingEntity: ShoppingEntity) -> Unit,
        onFailure: () -> Unit
    ) {
        shoppingLocalDataSource.registerShopping(fileName, onSuccess, onFailure)
    }

    override suspend fun isExistShoppingData(): Boolean = withContext(Dispatchers.IO) {
        return@withContext shoppingLocalDataSource.getAllShoppingData().list.isNotEmpty()
    }

    override suspend fun registerShoppingData(fileName: String): Result<ShoppingEntity> =
        withContext(Dispatchers.IO) {
            return@withContext shoppingLocalDataSource.registerShoppingData(fileName)
        }
}