package com.evendred.basicapp.mapper.json

import com.squareup.moshi.JsonAdapter
import javax.inject.Inject

class JsonMapper<T> @Inject constructor(private val jsonAdapter: JsonAdapter<T>) {
    fun fromJson(string: String): T = jsonAdapter.fromJson(string) ?: throw Exception("Json output is empty")
}