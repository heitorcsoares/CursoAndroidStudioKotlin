package com.example.filmes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmesDetails (
    val id: Int?,
    val title: String?,
    val overview: String?,
    val poster_path: String?,
    val vote_average: Double?
){
    fun getConteudoDetalhes(): String {
        return overview?.takeIf { it.isNotEmpty() } ?: "Conteúdo não disponivel. Volte mais tarde."
    }

    fun getImageUrl() = ApiCredentials.imageUrl + poster_path

}