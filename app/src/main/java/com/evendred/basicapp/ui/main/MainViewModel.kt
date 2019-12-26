package com.evendred.basicapp.ui.main

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.evendred.basicapp.extensions.cast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val handle: SavedStateHandle, private val controller: MainController): ViewModel() {

    class Factory(owner: SavedStateRegistryOwner, defaultArgs: Bundle?, private val controller: MainController): AbstractSavedStateViewModelFactory(owner, defaultArgs) {
        override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T = MainViewModel(handle, controller).cast()
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

    override fun onCleared() {
        //TODO saved instance states
    }
}