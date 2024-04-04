package com.example.filmes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmeImages(
    val path: String?,
    val extension: String?
){
    fun getFullImagePath(): String {
        val pathHttps = path?.replace("http", "https")

        return "$pathHttps.$extension"
    }
}