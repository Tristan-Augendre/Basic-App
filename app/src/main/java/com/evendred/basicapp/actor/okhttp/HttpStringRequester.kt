package com.evendred.basicapp.actor.okhttp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class HttpStringRequester(private val client: OkHttpClient = OkHttpClient()) {

    suspend fun getString(url: String): String = withContext(Dispatchers.IO) {
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()

        when(response.isSuccessful) {
            false -> throw IOException("Unexpected code $response")
            true -> response.body!!.string()
        }
    }
}