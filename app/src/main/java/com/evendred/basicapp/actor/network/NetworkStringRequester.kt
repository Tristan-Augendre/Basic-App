package com.evendred.basicapp.actor.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

class NetworkStringRequester @Inject constructor(private val httpClient: OkHttpClient, private val httpRequestBuilder: Request.Builder) {

    suspend fun getString(url: String): String = withContext(Dispatchers.IO) {
        val request = httpRequestBuilder.url(url).build()
        val response = httpClient.newCall(request).execute()

        when(response.isSuccessful) {
            false -> throw IOException("Unexpected code $response")
            true -> response.body!!.string()
        }
    }
}