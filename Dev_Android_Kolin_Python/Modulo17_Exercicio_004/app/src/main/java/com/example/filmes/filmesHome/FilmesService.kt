package com.example.filmes.filmesHome

import com.example.filmes.data.FilmesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmesService {

    @GET("/movie/latest")
    fun getListaFilmes(
        @Query("ts") timeStamp: String,
        @Query("apikey") API_KEY: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ) : Call<FilmesResponse>

    @GET("/movie/{movie_id}")
    fun getDetalhesFilme(
        @Query("ts") timeStamp: String,
        @Query("apikey") API_KEY: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ) : Call<FilmesResponse>

    @GET("/movie/{movie_id}/images")
    fun getImagemPosteres(
        @Query("ts") timeStamp: String,
        @Query("apikey") API_KEY: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ) : Call<FilmesResponse>
}