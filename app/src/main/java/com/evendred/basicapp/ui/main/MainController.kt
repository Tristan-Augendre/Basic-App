package com.evendred.basicapp.ui.main

import com.evendred.basicapp.actor.okhttp.HttpStringRequester
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainController @Inject constructor(private val requester: HttpStringRequester) {
    lateinit var presenter: WeakReference<MainPresenter>

    suspend fun onViewFirstCreate() {
        try {
            val response = requester.getString("https://jsonplaceholder.typicode.com/todos")
            presenter.get()?.displayOnScreen(response)
        } catch (e: Exception) {
            val error = e.message ?: "unknown error"
            presenter.get()?.displayOnScreen(error)
        }
    }
}