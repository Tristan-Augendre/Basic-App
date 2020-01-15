package com.evendred.basicapp.ui.champion.list

import androidx.lifecycle.*
import com.evendred.basicapp.extensions.cast
import kotlinx.coroutines.launch

class ChampionsListViewModel(private val controller: ChampionsListController): ViewModel() {

    class Factory(private val controller: ChampionsListController): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChampionsListViewModel(controller).cast()
    }

    private val championsList = MutableLiveData<List<Champion>>()
    private val errorSnackbar = MutableLiveData<String>()

    init {
        controller.presenter = ChampionsListPresenter(this)
        onCreated()
    }

    private fun onCreated() = viewModelScope.launch {
        controller.onViewCreated()
    }

    fun observeChampionsList(owner: LifecycleOwner, observer: (List<Champion>) -> Unit) {
        championsList.observe(owner::getLifecycle) { observer(it) }
    }

    fun observeErrorSnackbar(owner: LifecycleOwner, observer: (String) -> Unit) {
        errorSnackbar.observe(owner::getLifecycle) { observer(it) }
    }

    fun displayChampionsList(list: List<Champion>) {
        championsList.value = list
    }

    fun displayErrorSnackbar(error: String) {
        errorSnackbar.value = error
    }

    override fun onCleared() {
        controller.presenter = null
    }
}