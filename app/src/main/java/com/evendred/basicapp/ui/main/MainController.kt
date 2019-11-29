package com.evendred.basicapp.ui.main

import com.evendred.basicapp.actor.okhttp.HttpStringRequester
import javax.inject.Inject

class MainController @Inject constructor(private val requester: HttpStringRequester) {

    suspend fun getNetworkString(): String {
        return try {
            requester.getString("https://jsonplaceholder.typicode.com/todos")
        } catch (e: Exception) {
            e.message ?: "error"
        }
    }
}