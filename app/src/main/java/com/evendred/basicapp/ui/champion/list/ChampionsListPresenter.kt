package com.evendred.basicapp.ui.champion.list

class ChampionsListPresenter(private val view: ChampionsListViewModel) {

    fun displayChampionsList(championsList: List<Champion>) {
        view.displayChampionsList(championsList)
    }

    fun displayError(error: String) {
        view.displayErrorSnackbar(error)
    }
}