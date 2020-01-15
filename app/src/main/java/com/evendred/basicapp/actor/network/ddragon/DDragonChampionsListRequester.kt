package com.evendred.basicapp.actor.network.ddragon

import com.evendred.basicapp.actor.network.NetworkJsonRequester
import com.evendred.basicapp.mapper.Mapper
import com.evendred.basicapp.mapper.json.template.JsonChampionsList
import javax.inject.Inject

class DDragonChampionsListRequester<T> @Inject constructor(
    private val requester : NetworkJsonRequester<JsonChampionsList>,
    private val dataMapper: Mapper<JsonChampionsList, List<T>>) {

    companion object {
        private const val DATA_URL = "https://ddragon.leagueoflegends.com/cdn/%s/data/%s/champion.json"
        private const val IMG_URL = "https://ddragon.leagueoflegends.com/cdn/%s/img/champion/%s"
    }

    suspend fun getChampionsList(version: String = "10.1.1", language: String = "fr_FR"): List<T> {
        val url = String.format(DATA_URL, version, language)
        val json = requester.getJson(url)

        json.data.forEach {
            it.value.image.full = String.format(IMG_URL, version, it.value.image.full)
        }

        return dataMapper.map(json)
    }
}