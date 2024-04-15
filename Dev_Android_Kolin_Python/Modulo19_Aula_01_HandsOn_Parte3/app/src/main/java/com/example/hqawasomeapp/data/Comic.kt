package com.example.hqawasomeapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comic(
    val id: Int?,
    val title: String?,
    val description: String?,
    val textObjects: List<TextObject>?,
    val thumbnail: Image?
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

}

