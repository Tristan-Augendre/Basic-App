package com.evendred.basicapp.ui.champion.list

import com.evendred.basicapp.extensions.mock
import com.evendred.basicapp.mapper.json.template.JsonChampionsList
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.`when`

class ChampionsListMapperTest {
    private fun setup(): ChampionsListMapper = ChampionsListMapper()

    @Test
    fun mapJsonChampionsList() {
        //GIVEN
        val mapper = setup().apply {
            map = mock()
        }

        val jsonChampionsList: JsonChampionsList = mock()
        val jsonChampions: JsonChampionsList.JsonChampion = mock()
        val jsonChampionsListData: Map<String, JsonChampionsList.JsonChampion> = mapOf("id" to jsonChampions)
        val champion: Champion = mock()

        `when`(jsonChampionsList.data).thenReturn(jsonChampionsListData)
        `when`(mapper.map("id", jsonChampions)).thenReturn(champion)

        //WHEN
        val result = mapper.map(jsonChampionsList)

        //THEN
        assertEquals(listOf(champion), result)
    }

    @Test
    fun mapJsonChampion() {
        //GIVEN
        val mapper = setup()

        val jsonChampions: JsonChampionsList.JsonChampion = mock()
        val jsonImage: JsonChampionsList.Image = mock()
        `when`(jsonChampions.name).thenReturn("name")
        `when`(jsonChampions.title).thenReturn("title")
        `when`(jsonChampions.image).thenReturn(jsonImage)
        `when`(jsonImage.full).thenReturn("image")

        //WHEN
        val result = mapper.map("id", jsonChampions)

        //THEN
        assertEquals(result.id, "id")
        assertEquals(result.name, "name")
        assertEquals(result.title, "title")
        assertEquals(result.image, "image")
    }
}