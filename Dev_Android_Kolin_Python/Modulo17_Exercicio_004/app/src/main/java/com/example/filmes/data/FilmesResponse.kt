package com.example.filmes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmesResponse(
    val success : String?,
    val status_code : Int?,
    val status_message : String
)