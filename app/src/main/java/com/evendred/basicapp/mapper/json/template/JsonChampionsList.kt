package com.evendred.basicapp.mapper.json.template

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonChampionsList(
    val data: Map<String, JsonChampion>) {

    @JsonClass(generateAdapter = true)
    data class JsonChampion(
        val name: String,
        val title: String,
        val image: Image
    )

    @JsonClass(generateAdapter = true)
    data class Image(
        var full: String
    )
}