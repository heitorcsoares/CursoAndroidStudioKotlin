package com.example.filmes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmesResponse(
    var latest: List<FilmeLatest>,
    val details: List<FilmeDetails>,
    val images: FilmeImages
)