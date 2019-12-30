package com.evendred.basicapp.ui.main

class MainPresenter(var view: MainViewModel) {

    fun displayOnScreen(string: String) {
        view.setTextValue(string)
    }
}