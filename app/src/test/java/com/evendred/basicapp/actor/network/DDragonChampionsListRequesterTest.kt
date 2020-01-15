package com.evendred.basicapp.actor.network

import com.evendred.basicapp.actor.network.ddragon.DDragonChampionsListRequester
import com.evendred.basicapp.extensions.mock
import com.evendred.basicapp.mapper.Mapper
import com.evendred.basicapp.mapper.json.template.JsonChampionsList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.`when`

class DDragonChampionsListRequesterTest {
    private val dataRequester : NetworkJsonRequester<JsonChampionsList> = mock()
    private val dataMapper: Mapper<JsonChampionsList, List<Any>> = mock()

    private fun setup(): DDragonChampionsListRequester<Any> = DDragonChampionsListRequester(
        dataRequester,
        dataMapper
    )

    @Test
    fun getChampionsList() = runBlocking {
        //GIVEN
        val requester = setup()

        val jsonChampionsList: JsonChampionsList = mock()
        val jsonChampion: JsonChampionsList.JsonChampion = mock()
        val data: Map<String, JsonChampionsList.JsonChampion> = mapOf("key" to jsonChampion)
        val image: JsonChampionsList.Image = mock()
        val championsListResult = listOf(Any())

        `when`(dataRequester.getJson("https://ddragon.leagueoflegends.com/cdn/version/data/language/champion.json")).thenReturn(jsonChampionsList)
        `when`(jsonChampionsList.data).thenReturn(data)
        `when`(jsonChampion.image).thenReturn(image)
        `when`(image.full).thenReturn("image")
        `when`(dataMapper.map(jsonChampionsList)).thenReturn(championsListResult)

        //WHEN
        val championsList = requester.getChampionsList("version", "language")

        //THEN
        assertEquals(championsListResult, championsList)
    }
}