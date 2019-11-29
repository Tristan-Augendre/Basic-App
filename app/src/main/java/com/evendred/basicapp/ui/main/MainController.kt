package com.evendred.basicapp.ui.main

import com.evendred.basicapp.actor.okhttp.HttpStringRequester

class MainController(private val requester: HttpStringRequester = HttpStringRequester()) {

    suspend fun getNetworkString(): String {
        return try {
            requester.getString("https://jsonplaceholder.typicode.com/todos")
        } catch (e: Exception) {
            e.message ?: "error"
        }
    }
}