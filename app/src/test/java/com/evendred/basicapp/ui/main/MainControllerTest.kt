package com.evendred.basicapp.ui.main

import com.evendred.basicapp.actor.network.NetworkJsonRequester
import com.evendred.basicapp.extensions.mock
import com.evendred.basicapp.model.Todo
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class MainControllerTest {
    private val model: MainModel = mock()
    private val requester: NetworkJsonRequester<List<Todo>> = mock()
    private val presenter: MainPresenter = mock()

    private fun setup(): MainController = MainController(model, requester).apply {
        presenter = this@MainControllerTest.presenter
    }

    @Test
    fun onViewCreatedWhenRequesterSuccess() = runBlocking {
        //GIVEN
        val controller = setup()

        val todo: Todo = mock()
        `when`(requester.getJson("https://jsonplaceholder.typicode.com/todos")).thenReturn(listOf(todo))
        `when`(todo.title).thenReturn("title")

        //WHEN
        controller.onViewCreated()

        //THEN
        verify(presenter).displayOnScreen("title")
    }

    @Test
    fun onViewCreatedWhenRequesterFailWithMessage() = runBlocking {
        //GIVEN
        val controller = setup()

        `when`(requester.getJson("https://jsonplaceholder.typicode.com/todos")).thenThrow(RuntimeException("message"))

        //WHEN
        controller.onViewCreated()

        //THEN
        verify(presenter).displayOnScreen("message")
    }

    @Test
    fun onViewCreatedWhenRequesterFailWithoutMessage() = runBlocking {
        //GIVEN
        val controller = setup()

        `when`(requester.getJson("https://jsonplaceholder.typicode.com/todos")).thenThrow(RuntimeException())

        //WHEN
        controller.onViewCreated()

        //THEN
        verify(presenter).displayOnScreen("unknown error")
    }

    @Test
    fun onEditTextChanged() {
        //GIVEN
        val controller = setup()

        //WHEN
        controller.onEditTextChanged("editText")

        //THEN
        verify(model).edit = "editText"
    }

    @Test
    fun onClick() {
        //GIVEN
        val controller = setup()

        `when`(model.edit).thenReturn("edit")

        //WHEN
        controller.onClick()

        //THEN
        verify(presenter).displayOnScreen("edit")
    }
}