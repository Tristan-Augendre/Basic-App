package com.evendred.basicapp.ui.main

import com.evendred.basicapp.extensions.mock
import org.junit.jupiter.api.Test

import org.mockito.Mockito.verify

class MainPresenterTest {
    private val view: MainViewModel = mock()

    private fun setup(): MainPresenter = MainPresenter(view)

    @Test
    fun displayOnScreen() {
        //GIVEN
        val presenter = setup()

        //WHEN
        presenter.displayOnScreen("string")

        //THEN
        verify(view).setTextValue("string")
    }
}