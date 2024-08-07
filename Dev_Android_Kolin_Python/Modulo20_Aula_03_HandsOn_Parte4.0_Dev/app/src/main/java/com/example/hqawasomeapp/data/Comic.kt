package com.example.hqawasomeapp.data

import com.squareup.moshi.JsonClass
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@JsonClass(generateAdapter = true)
data class Comic(
    val id: Int?,
    val title: String?,
    val description: String?,
    val textObjects: List<TextObject>?,
    val thumbnail: Image?,
    val images: List<Image>?
){
    fun getContent(): String{
        return when{
            description?.isNotEmpty() == true -> description
            textObjects?.isNotEmpty() == true -> textObjects[0].text ?: "Conteudo não disponivel."
            else -> "Conteudo não disponivel."
        }
    }

    fun getIdString(): String {
        return id?.toString() ?: ""
    }

    /** Função recupera o caminho completo da imagem
     * Utiliza (?) operador de chamada segura caso imagem seja NULA.  * */
    fun getImageUrl() = thumbnail?.getFullImagePath()

    fun getCarouselImages(): List<CarouselItem>? = images?.map {                                 /** Converte array de imagens para um carouselItem */
        CarouselItem(imageUrl = it.getFullImagePath())
    }

}