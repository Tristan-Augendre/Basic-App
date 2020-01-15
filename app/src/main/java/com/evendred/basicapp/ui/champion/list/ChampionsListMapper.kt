package com.evendred.basicapp.ui.champion.list

import com.evendred.basicapp.mapper.Mapper
import com.evendred.basicapp.mapper.json.template.JsonChampionsList

class ChampionsListMapper: Mapper<JsonChampionsList, List<Champion>> {
    override fun map(from: JsonChampionsList): List<Champion> = from.data.map {
        map(it.key, it.value)
    }

    var map: (String, JsonChampionsList.JsonChampion) -> Champion = { id, from -> Champion(
        id = id,
        name = from.name,
        title = from.title,
        image = from.image.full
    )}
}