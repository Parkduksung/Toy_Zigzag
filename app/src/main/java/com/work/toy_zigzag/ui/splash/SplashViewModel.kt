package com.work.toy_zigzag.ui.splash

import android.util.Log
import androidx.lifecycle.*
import com.work.toy_zigzag.data.repository.ShoppingRepository
import com.work.toy_zigzag.util.Shopping
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class SplashViewModel : ViewModel(), LifecycleObserver {

    private val _onEventLiveData = MutableLiveData<OnEvent>()
    val onEventLiveData: LiveData<OnEvent> = _onEventLiveData

    private val splashInteractor: SplashInteractor by inject(SplashInteractor::class.java)


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun checkExistShoppingList() {
        CoroutineScope(Dispatchers.Main).launch {
            if (splashInteractor.isExistShoppingData()) {
                _onEventLiveData.value = OnEvent.RouteMain
            } else {
                if (splashInteractor.registerShoppingData(fileName = FILE_NAME).isSuccess) {
                    _onEventLiveData.value = OnEvent.RouteMain
                }
            }
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun initLiveDataState() {
        _onEventLiveData.value = null
    }


    companion object {
        private const val FILE_NAME = "shop_list.json"
    }

    sealed class OnEvent {
        object RouteMain : OnEvent()
        data class IsExistSHoppingData(val isExist: Boolean) : OnEvent()
    }
}