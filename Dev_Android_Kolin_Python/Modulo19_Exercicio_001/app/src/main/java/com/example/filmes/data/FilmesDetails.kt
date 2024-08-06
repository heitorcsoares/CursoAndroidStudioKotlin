package com.example.filmes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class FilmesDetails (
    val title: String?,
    val overview: String?,
    val poster_path: String?
){
    fun getConteudoDetalhes(): String {
        return overview?.takeIf { it.isNotEmpty() } ?: "Conteúdo não disponivel. Volte mais tarde."
    }
}