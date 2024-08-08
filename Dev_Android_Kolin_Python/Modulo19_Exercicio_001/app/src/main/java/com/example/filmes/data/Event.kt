package com.example.filmes.data

class Event<out T>(private val content: T) {

    var hasBeenHandler = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandler) {
            null
        } else {
            hasBeenHandler = true
            content
        }
    }
}