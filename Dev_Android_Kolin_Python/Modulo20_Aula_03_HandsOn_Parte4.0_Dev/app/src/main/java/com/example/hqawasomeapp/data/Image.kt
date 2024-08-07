package com.example.hqawasomeapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    val path: String?,
    val extension: String?
){

    /** •Converte http em https | •retorna PathHttps+extensão formando a URL completa da Imagem  */
    fun getFullImagePath(): String {
        val pathHttps = path?.replace("http", "https")

        return "$pathHttps.$extension"
    }
}