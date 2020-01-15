package com.evendred.basicapp.model

class Event<T>(private var content: T) {
    private var consumed: Boolean = false

    fun consume(): T? {
        if (consumed) return null
        consumed = true
        return content
    }
}