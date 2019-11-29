package com.evendred.basicapp.ui.main

import androidx.lifecycle.*
import com.evendred.basicapp.extensions.cast
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(private val controller: MainController): ViewModel() {
    @Module
    class MainViewModelModule(private var viewModelStoreOwner: ViewModelStoreOwner) {
        @Provides
        fun provideMainViewModel(viewModelFactory: Factory): MainViewModel {
            return ViewModelProvider(viewModelStoreOwner, viewModelFactory).get()
        }
    }

    class Factory @Inject constructor(private val controller: MainController): ViewModelProvider.Factory {
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