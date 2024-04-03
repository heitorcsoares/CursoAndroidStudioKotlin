package com.example.filmes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmesResponse(
    val movieId: Int?,
    val details: String?,
    val images: String?
)