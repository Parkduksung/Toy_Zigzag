package com.work.toy_zigzag.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {


    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData


    protected fun viewStateChanged(viewState: ViewState){
        _viewStateLiveData.value = viewState
        _viewStateLiveData.value = null
    }

}

interface ViewState