package com.evendred.basicapp.ui.main

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.evendred.basicapp.extensions.cast
import com.evendred.basicapp.extensions.weak
import kotlinx.coroutines.launch

class MainViewModel(private val handle: SavedStateHandle, private val controller: MainController): ViewModel() {

    class Factory(owner: SavedStateRegistryOwner, defaultArgs: Bundle?, private val controller: MainController): AbstractSavedStateViewModelFactory(owner, defaultArgs) {
        override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T = MainViewModel(handle, controller).cast()
    }

    val text = MutableLiveData<String>()
    val edit = MutableLiveData<String>()

    init {
        controller.presenter = MainPresenter(this).weak()
        onCreated()
    }

    private fun onCreated() = viewModelScope.launch {
        controller.onViewFirstCreate()
    }

    override fun onCleared() {
        //TODO saved instance states
    }

    fun onClick() {
        text.value = edit.value
    }

    fun setTextValue(value: String) {
        text.value = value
    }
}