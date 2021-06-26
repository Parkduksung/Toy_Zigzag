package com.work.toy_zigzag.ui.splash

import android.util.Log
import androidx.lifecycle.*
import com.work.toy_zigzag.data.repository.ShoppingRepository
import com.work.toy_zigzag.util.Shopping

class SplashViewModel(
    private val shoppingRepository: ShoppingRepository
) : ViewModel(), LifecycleObserver {

    private val _onEventLiveData = MutableLiveData<OnEvent>()
    val onEventLiveData: LiveData<OnEvent> = _onEventLiveData

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun checkExistItem() {
        shoppingRepository.getAll(
            onSuccess = { shoppingEntity ->

                val toShoppingItem = shoppingEntity.toShoppingItem()

                if (toShoppingItem.list.isNotEmpty()) {
                    Shopping.saveStyleSort(toShoppingItem)
                    _onEventLiveData.value = OnEvent.RouteMain
                } else {
                    registerShopping()
                }
            },
            onFailure = {
                registerShopping()
            }
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun initLiveDataState() {
        _onEventLiveData.value = null
    }

    private fun registerShopping() {
        shoppingRepository.registerShopping(
            FILE_NAME,
            onSuccess = { shoppingEntity ->
                Log.d("결과", "여기타나?onSuccessshoppingEntity")
                Shopping.saveStyleSort(shoppingEntity.toShoppingItem())
                _onEventLiveData.value = OnEvent.RouteMain
            },
            onFailure = {
                Log.d("결과", "여기타나?onFailureshoppingEntity")
            })
    }

    companion object {
        private const val FILE_NAME = "shop_list.json"
    }

    sealed class OnEvent {
        object RouteMain : OnEvent()
    }
}