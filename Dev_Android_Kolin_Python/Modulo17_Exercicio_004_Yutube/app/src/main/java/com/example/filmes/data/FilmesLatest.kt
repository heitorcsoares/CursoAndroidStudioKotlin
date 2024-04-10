package com.example.filmes.data

import com.squareup.moshi.JsonClass

/** Classe responsavel saber o status da Request (solicitação) */
/** Dados Globais */
@JsonClass(generateAdapter = true)
class FilmesLatest (
    val id: Int?,
    val title: String?,
    val overview: Int?,
    val popularity: Int?,
    val release_date: String?
)