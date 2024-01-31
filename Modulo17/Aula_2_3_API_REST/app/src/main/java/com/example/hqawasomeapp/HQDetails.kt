package com.example.hqawasomeapp

import com.squareup.moshi.JsonClass
import java.time.Year

/**data class -> classe para armazenar e gerenciar Dados.*/
data class HQDetails (
    val id: Int,
    val title: String,
    val year: String,
    val genres: List<String>,
    val content: String){

    @JsonClass(generateAdapter = true)
}