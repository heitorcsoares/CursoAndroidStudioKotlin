package com.example.filmes.filmesHome

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmesService {

    @GET("/movie/latest")
    fun getListaFilmes(

    )

    @GET("/movie/{movie_id}")
    fun getDetalhesFilme(

    )

    @GET("/movie/{movie_id}/images")
    fun getPosteres(

    )

}