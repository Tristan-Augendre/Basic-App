package com.evendred.basicapp.ui.champion.list

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.evendred.basicapp.extensions.cast
import com.evendred.basicapp.model.Event
import com.evendred.basicapp.ui.champion.detail.ChampionDetailFragment
import kotlinx.coroutines.launch

class ChampionsListViewModel(private val controller: ChampionsListController): ViewModel() {

    class Factory(private val controller: ChampionsListController): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChampionsListViewModel(controller).cast()
    }

    private val championsList = MutableLiveData<List<Champion>>()
    private val errorSnackbar = MutableLiveData<String>()
    private val navigate = MutableLiveData<Event<NavDirections>>()

    init {
        controller.presenter = ChampionsListPresenter(this)
        onCreated()
    }

    private fun onCreated() = viewModelScope.launch {
        controller.onCreated()
    }

    fun observeChampionsList(owner: LifecycleOwner, observer: (List<Champion>) -> Unit) {
        championsList.observe(owner::getLifecycle) { observer(it) }
    }

    fun observeErrorSnackbar(owner: LifecycleOwner, observer: (String) -> Unit) {
        errorSnackbar.observe(owner::getLifecycle) { observer(it) }
    }

    fun observeNavigate(owner: LifecycleOwner, observer: (NavDirections) -> Unit) {
        navigate.observe(owner::getLifecycle) { it.consume()?.let { nav -> observer(nav) } }
    }

    fun onViewCreated() {
        controller.onViewCreated()
    }

    fun onChampionCLick(champion: Champion) {
        controller.onChampionCLick(champion)
    }

    fun displayChampionsList(list: List<Champion>) {
        championsList.value = list
    }

    fun displayErrorSnackbar(error: String) {
        errorSnackbar.value = error
    }

    fun navigateToChampionDetail(championId: String) {
        navigate.value = Event(ChampionDetailFragment.getNavDirection(championId))
    }

    override fun onCleared() {
        controller.presenter = null
    }
}