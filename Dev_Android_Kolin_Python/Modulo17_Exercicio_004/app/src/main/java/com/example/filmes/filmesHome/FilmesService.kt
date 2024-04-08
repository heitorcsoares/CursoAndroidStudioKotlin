package com.example.filmes.filmesHome

import com.example.filmes.data.FilmesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmesService {

    @GET("movie/latest")
    fun getFilmesLista(
        @Query("api_key") apikey: String,
        @Query("limit") limit: Int
    ) : Call<FilmesResponse>

}