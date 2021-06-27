package com.work.toy_zigzag.ui.splash

import com.work.toy_zigzag.data.repository.ShoppingRepository
import com.work.toy_zigzag.network.room.entity.ShoppingEntity
import com.work.toy_zigzag.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class SplashInteractor {

    private val shoppingRepository: ShoppingRepository by inject(ShoppingRepository::class.java)

    suspend fun isExistShoppingData(): Boolean = withContext(Dispatchers.IO) {
        return@withContext shoppingRepository.isExistShoppingData()
    }

    suspend fun registerShoppingData(fileName: String): Result<ShoppingEntity> =
        withContext(Dispatchers.IO) {
            return@withContext when (val registerShoppingData =
                shoppingRepository.registerShoppingData(fileName)) {
                Result.success(registerShoppingData) -> {
                    shoppingRepository.registerShoppingData(fileName)
                }
                Result.failure<ShoppingEntity>(Exception("Error")) -> {
                    throw Exception()
                }

                else -> throw Exception()
            }
        }
}