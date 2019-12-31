package com.evendred.basicapp.actor.network

import com.evendred.basicapp.mapper.json.JsonMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkJsonRequester<T> @Inject constructor(private val stringRequester: NetworkStringRequester, private val jsonMapper: JsonMapper<T>) {
    suspend fun getJson(url: String): T = withContext(Dispatchers.Default) {
        val response = stringRequester.getString(url)
        jsonMapper.fromJson(response)
    }
}