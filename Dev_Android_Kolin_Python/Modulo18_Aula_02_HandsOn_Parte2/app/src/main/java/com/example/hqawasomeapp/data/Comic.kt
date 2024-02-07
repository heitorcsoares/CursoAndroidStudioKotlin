package com.example.hqawasomeapp.data

import android.media.Image
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comic(
    val id: Int?,
    val title: String?,
    val description: String?,
    val textObjects: List<TextObject>?,
    val thumbnail: Image?
){
    fun getContent(): String? {
        return when{
            description?.isNotEmpty() == true -> description
            textObjects?.isNotEmpty() == true -> textObjects[0].text ?: "Conteúdo não disponivel"
            else -> "Conteúdo não disponivel"
        }
    }

    fun getIdString(): String {
        return id?.toString() ?: ""
    }

    fun getImageUlr() = thumbnail?.getFullImagePath()
}
