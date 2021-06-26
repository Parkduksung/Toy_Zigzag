package com.work.toy_zigzag.data.source.local

import com.work.toy_zigzag.network.room.database.ShoppingDatabase
import com.work.toy_zigzag.network.room.entity.ShoppingEntity
import com.work.toy_zigzag.util.AppExecutors
import com.work.toy_zigzag.util.ConvertJson
import com.work.toy_zigzag.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoppingLocalDataSourceImpl(
    private val appExecutors: AppExecutors,
    private val shoppingDatabase: ShoppingDatabase
) :
    ShoppingLocalDataSource {
    override fun getAll(
        onSuccess: (shoppingEntity: ShoppingEntity) -> Unit,
        onFailure: () -> Unit
    ) {
        appExecutors.diskIO.execute {

            val getItem =
                shoppingDatabase.shoppingListDao().getAll()

            appExecutors.mainThread.execute {
                onSuccess(getItem)
            }
        }
    }

    override fun registerShopping(
        fileName: String,
        onSuccess: (shoppingEntity: ShoppingEntity) -> Unit,
        onFailure: () -> Unit
    ) {
        appExecutors.diskIO.execute {

            val getShoppingResponse =
                ConvertJson.getShoppingList(fileName)

            val toShoppingDocuments =
                getShoppingResponse.list.map { it.toShoppingDocument() }

            val shoppingEntity =
                ShoppingEntity(
                    week = getShoppingResponse.week,
                    list = toShoppingDocuments
                )

            val registerShoppingList =
                shoppingDatabase.shoppingListDao()
                    .registerShoppingList(shoppingEntity)

            appExecutors.mainThread.execute {
                if (registerShoppingList >= 1) {
                    onSuccess(shoppingEntity)
                } else {
                    onFailure()
                }
            }
        }
    }

    override suspend fun getAllShoppingData(): ShoppingEntity = withContext(Dispatchers.IO) {
        return@withContext shoppingDatabase.shoppingListDao().getAll()
    }

}