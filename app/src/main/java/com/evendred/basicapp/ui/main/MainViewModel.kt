package com.evendred.basicapp.ui.main

import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.evendred.basicapp.extensions.cast
import kotlinx.coroutines.launch

class MainViewModel(private val handle: SavedStateHandle, private val controller: MainController): ViewModel() {

    class Factory(owner: SavedStateRegistryOwner, defaultArgs: Bundle?, private val controller: MainController): AbstractSavedStateViewModelFactory(owner, defaultArgs) {
        override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T = MainViewModel(handle, controller).cast()
    }

    val text = MutableLiveData<String>()
    val editText = MutableLiveData<String>()

    init {
        controller.onViewBind(MainPresenter(this))
        onCreated()
    }

    private fun onCreated() = viewModelScope.launch {
        controller.onViewCreated()
    }

    fun onEditTextChanged(text: Editable?) {
        controller.onEditTextChanged(text.toString())
    }

    fun onClick() {
        controller.onClick()
    }

    fun setTextValue(value: String) {
        text.value = value
    }

    override fun onCleared() {
        controller.onViewCleared()
    }
}