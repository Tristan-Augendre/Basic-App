package com.evendred.basicapp.ui.champion.list

import com.evendred.basicapp.actor.network.ddragon.DDragonChampionsListRequester
import com.evendred.basicapp.extensions.mock
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class ChampionsListControllerTest {
    private val requester: DDragonChampionsListRequester<Champion> = mock()
    private val presenter: ChampionsListPresenter = mock()

    private fun setup(model: ChampionsListModel = mock()): ChampionsListController = ChampionsListController(model, requester).apply {
        presenter = this@ChampionsListControllerTest.presenter
    }

    @Test
    fun onViewCreatedWhenRequesterSuccess() = runBlocking {
        //GIVEN
        val model = spy(ChampionsListModel(mock()))
        val controller = setup(model)

        val championsList: List<Champion> = mock()
        `when`(requester.getChampionsList()).thenReturn(championsList)

        //WHEN
        controller.onViewCreated()

        //THEN
        verify(model).championsList = championsList
        verify(presenter).displayChampionsList(championsList)
    }

    @Test
    fun onViewCreatedWhenRequesterFail() = runBlocking {
        //GIVEN
        val controller = setup()

        `when`(requester.getChampionsList()).thenThrow(RuntimeException("error"))

        //WHEN
        controller.onViewCreated()

        //THEN
        verify(presenter).displayError("error")
    }

    @Test
    fun onViewCreatedWhenRequesterUnknownFail() = runBlocking {
        //GIVEN
        val controller = setup()

        `when`(requester.getChampionsList()).thenThrow(RuntimeException())

        //WHEN
        controller.onViewCreated()

        //THEN
        verify(presenter).displayError("unknown error")
    }
}