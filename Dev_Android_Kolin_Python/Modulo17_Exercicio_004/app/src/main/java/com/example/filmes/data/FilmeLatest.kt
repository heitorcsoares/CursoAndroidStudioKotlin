package com.example.filmes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmeLatest(
    val overview: String?
)