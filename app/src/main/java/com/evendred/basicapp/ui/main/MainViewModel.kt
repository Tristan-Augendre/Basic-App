package com.evendred.basicapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.evendred.basicapp.extensions.cast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val controller: MainController): ViewModel() {

    class Factory(private val controller: MainController = MainController()): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(controller).cast()
    }

    val text = MutableLiveData<String>()
    val edit = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            text.value = controller.getNetworkString()
        }
    }

    fun onClick() = viewModelScope.launch(Dispatchers.Main.immediate) {
        text.value = edit.value
    }
}