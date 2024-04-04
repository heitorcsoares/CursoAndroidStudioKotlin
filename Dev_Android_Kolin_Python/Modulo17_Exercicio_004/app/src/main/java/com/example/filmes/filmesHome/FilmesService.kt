package com.example.filmes.filmesHome

import com.example.filmes.data.FilmesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmesService {

    @GET("/movie/latest")
    fun getListaFilmes(
        @Query("api_key") apikey: String
    ): Call<FilmesResponse>

    @GET("/movie/{movie_id}")
    fun getDetalhesFilme(
        @Query("api_key") apikey: String
    ) : Call<FilmesResponse>

    @GET("/movie/{movie_id}/images")
    fun getImagemPosteres(
        @Query("api_key") apikey: String
    ) : Call<FilmesResponse>
}