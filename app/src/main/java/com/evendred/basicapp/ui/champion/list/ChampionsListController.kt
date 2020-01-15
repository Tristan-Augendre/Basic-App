package com.evendred.basicapp.ui.champion.list

import com.evendred.basicapp.actor.network.ddragon.DDragonChampionsListRequester
import javax.inject.Inject

class ChampionsListController @Inject constructor(private val model: ChampionsListModel, private val requester: DDragonChampionsListRequester<Champion>) {
    var presenter: ChampionsListPresenter? = null

    suspend fun onViewCreated() {
        try {
            model.championsList = requester.getChampionsList()
            presenter?.displayChampionsList(model.championsList)
        } catch (e: Exception) {
            val error = e.message ?: "unknown error"
            presenter?.displayError(error)
        }
    }
}