package com.work.toy_zigzag.data.source.local

import com.work.toy_zigzag.network.room.database.ShoppingDatabase
import com.work.toy_zigzag.network.room.entity.ShoppingEntity
import com.work.toy_zigzag.util.AppExecutors
import com.work.toy_zigzag.util.ConvertJson

class ShoppingLocalDataSourceImpl(
    private val appExecutors: AppExecutors,
    private val shoppingDatabase: ShoppingDatabase
) :
    ShoppingLocalDataSource {
    override fun getAll(callback: (shoppingEntity: ShoppingEntity?) -> Unit) {
        appExecutors.diskIO.execute {

            val getItem =
                shoppingDatabase.shoppingListDao().getAll()

            AppExecutors().mainThread.execute {
                callback(getItem)
            }
        }
    }

    override fun registerShopping(
        fileName: String,
        callback: (shoppingEntity: ShoppingEntity?) -> Unit
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
                    callback(shoppingEntity)
                } else {
                    callback(null)
                }
            }
        }
    }

}