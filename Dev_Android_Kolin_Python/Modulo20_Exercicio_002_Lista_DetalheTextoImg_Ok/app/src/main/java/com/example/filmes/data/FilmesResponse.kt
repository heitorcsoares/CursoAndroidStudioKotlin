package com.example.filmes.data

import com.example.filmes.data.Filmes
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmesResponse (

    val results: List<Filmes>?

)