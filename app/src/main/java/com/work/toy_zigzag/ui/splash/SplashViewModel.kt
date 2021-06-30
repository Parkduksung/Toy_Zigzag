package com.work.toy_zigzag.ui.splash

import android.app.Application
import androidx.lifecycle.*
import com.work.toy_zigzag.base.BaseViewModel
import com.work.toy_zigzag.base.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SplashViewModel(application: Application) : BaseViewModel(application), LifecycleObserver {

    private val _onEventLiveData = MutableLiveData<SplashViewState>()
    val splashViewStateLiveData: LiveData<SplashViewState> = _onEventLiveData

    private val splashInteractor: SplashInteractor by inject(SplashInteractor::class.java)


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun checkExistShoppingList() {
        CoroutineScope(Dispatchers.Main).launch {
            if (splashInteractor.isExistShoppingData()) {
                _onEventLiveData.value = SplashViewState.RouteMain
            } else {
                if (splashInteractor.registerShoppingData(fileName = FILE_NAME).isSuccess) {
                    _onEventLiveData.value = SplashViewState.RouteMain
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

    sealed class SplashViewState : ViewState  {
        object RouteMain : SplashViewState()
        data class IsExistSHoppingData(val isExist: Boolean) : SplashViewState()
    }



}