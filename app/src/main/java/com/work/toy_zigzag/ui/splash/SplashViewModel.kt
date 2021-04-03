package com.work.toy_zigzag.ui.splash

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
                val toShoppingItem =
                    shoppingEntity.toShoppingItem()
                if (toShoppingItem.list.isNotEmpty()) {
                    Shopping.saveStyleSort(toShoppingItem)
                    _onEventLiveData.value = OnEvent.RouteMain
                } else {
                    registerShopping()
                }
            },
            onFailure = {

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
                Shopping.saveStyleSort(shoppingEntity.toShoppingItem())
                _onEventLiveData.value = OnEvent.RouteMain
            },
            onFailure = {
            })
    }

    companion object {
        private const val FILE_NAME = "shop_list.json"
    }

    sealed class OnEvent {
        object RouteMain : OnEvent()
    }
}