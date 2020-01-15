package com.evendred.basicapp.ui.champion.list

import com.evendred.basicapp.extensions.mock
import org.junit.jupiter.api.Test

import org.mockito.Mockito.verify

class ChampionsListPresenterTest {
    private val view: ChampionsListViewModel = mock()

    private fun setup(): ChampionsListPresenter = ChampionsListPresenter(view)

    @Test
    fun displayChampionsList() {
        //GIVEN
        val presenter = setup()

        val championsList: List<Champion> = listOf(mock())

        //WHEN
        presenter.displayChampionsList(championsList)

        //THEN
        verify(view).displayChampionsList(championsList)
    }

    @Test
    fun displayError() {
        //GIVEN
        val presenter = setup()

        //WHEN
        presenter.displayError("error")

        //THEN
        verify(view).displayErrorSnackbar("error")
    }

    @Test
    fun navigateToChampionDetail() {
        //GIVEN
        val presenter = setup()

        //WHEN
        presenter.navigateToChampionDetail("championId")

        //THEN
        verify(view).navigateToChampionDetail("championId")
    }
}