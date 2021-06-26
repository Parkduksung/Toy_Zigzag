package com.work.toy_zigzag.ui.splash

import com.work.toy_zigzag.data.repository.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class SplashInteractor {

    private val shoppingRepository: ShoppingRepository by inject(ShoppingRepository::class.java)

    suspend fun isExistShoppingData(): Boolean = withContext(Dispatchers.IO) {
        return@withContext shoppingRepository.isExistShoppingData()
    }
}