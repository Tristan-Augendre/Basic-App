package com.evendred.basicapp.ui.main

class MainPresenter(private val view: MainViewModel) {
    fun displayOnScreen(string: String) {
        view.setTextValue(string)
    }
}