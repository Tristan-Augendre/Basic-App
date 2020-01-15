package com.evendred.basicapp.mapper.json

import com.evendred.basicapp.mapper.Mapper
import com.squareup.moshi.JsonAdapter
import javax.inject.Inject

class JsonMapper<T> @Inject constructor(private val jsonAdapter: JsonAdapter<T>): Mapper<String, T> {
    override fun map(from: String): T = jsonAdapter.fromJson(from) ?: throw Exception("Json output is empty")
}