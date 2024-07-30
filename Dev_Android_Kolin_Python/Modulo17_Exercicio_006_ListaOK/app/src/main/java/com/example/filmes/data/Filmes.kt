package com.example.filmes.data

import com.example.filmes.api.ApiCredentials
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Filmes (
    val id: Int?,
    val title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String
){

    /** Verifica se há conteudo na Descrição do Filme */
    fun getConteudo(): String {
        return when{
            overview?.isNotEmpty() == true -> overview
            else -> "Conteudo não disponivel. Volte mais tarde."
        }
    }

    /** Caminho para Imagem de Capa */
    fun getFullImagePath(): String {
        val pathHttps = ApiCredentials.IMG_POSTER

        return "$pathHttps$poster_path"
    }

}

/** https://api.themoviedb.org/3/movie/{movie_id}/images */