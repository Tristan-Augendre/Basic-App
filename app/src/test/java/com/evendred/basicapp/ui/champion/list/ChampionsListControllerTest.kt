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
    fun onCreatedWhenRequesterSuccess() = runBlocking {
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
    fun onCreatedWhenRequesterFail() = runBlocking {
        //GIVEN
        val controller = setup()

        `when`(requester.getChampionsList()).thenThrow(RuntimeException("error"))

        //WHEN
        controller.onViewCreated()

        //THEN
        verify(presenter).displayError("error")
    }

    @Test
    fun onCreatedWhenRequesterUnknownFail() = runBlocking {
        //GIVEN
        val controller = setup()

        `when`(requester.getChampionsList()).thenThrow(RuntimeException())

        //WHEN
        controller.onViewCreated()

        //THEN
        verify(presenter).displayError("unknown error")
    }

    @Test
    fun onViewCreated() {
        //GIVEN
        val model: ChampionsListModel = mock()
        val controller = setup(model)

        val championsList: List<Champion> = mock()
        `when`(model.championsList).thenReturn(championsList)

        //WHEN
        controller.onViewCreated()

        //THEN
        verify(presenter).displayChampionsList(championsList)
    }

    @Test
    fun onChampionCLick() {
        //GIVEN
        val controller = setup()

        val champion: Champion = mock()
        `when`(champion.id).thenReturn("id")

        //WHEN
        controller.onChampionCLick(champion)

        //THEN
        verify(presenter).navigateToChampionDetail("id")
    }
}