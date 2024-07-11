package com.example.filmes.filmesHome

import com.example.filmes.api.ApiCredentials
import com.example.filmes.data.FilmesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmesService {

    @GET("https://api.themoviedb.org/3/movie/now_playing")
    fun getFilmesLista(
        @Query("API_KEY") apikey: String = ApiCredentials.API_KEY
    ) : Call<FilmesResponse>

}