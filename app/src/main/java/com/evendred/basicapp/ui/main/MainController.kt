package com.evendred.basicapp.ui.main

import com.evendred.basicapp.actor.network.HttpJsonRequester
import com.evendred.basicapp.model.Todo
import javax.inject.Inject

class MainController @Inject constructor(private var model: MainModel, private val requester: HttpJsonRequester<List<Todo>>) {
    var presenter: MainPresenter? = null

    suspend fun onViewCreated() {
        try {
            val response = requester.getJson("https://jsonplaceholder.typicode.com/todos")
            presenter?.displayOnScreen(response.first().title)
        } catch (e: Exception) {
            val error = e.message ?: "unknown error"
            presenter?.displayOnScreen(error)
        }
    }

    fun onEditTextChanged(editText: String) {
        model.edit = editText
    }

    fun onClick() {
        presenter?.displayOnScreen(model.edit)
    }
}