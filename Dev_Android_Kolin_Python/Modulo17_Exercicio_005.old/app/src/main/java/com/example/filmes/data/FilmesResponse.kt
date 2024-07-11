package com.example.filmes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class FilmesResponse (
    val results: List<Filmes>?
)