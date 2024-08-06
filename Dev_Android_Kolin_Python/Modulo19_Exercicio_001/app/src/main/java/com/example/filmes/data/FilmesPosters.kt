package com.example.filmes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class FilmesPosters(
    var id: Int?
){

}