package com.example.hqawasomeapp.placeholder

/** Classe auxiliar para controlar o estado da leitura dos dados */
data class DataState<T>(
    val state: State,
    val data: T? = null)