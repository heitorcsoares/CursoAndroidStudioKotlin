package com.example.filmes.data

import com.squareup.moshi.JsonClass
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

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

    /**
    fun getCarouselImages(): List<CarouselItem>? = images?.map {
        CarouselItem(imageUrl = it.getFullImagePath())
    }
    */

}

/** https://api.themoviedb.org/3/movie/{movie_id}/images */