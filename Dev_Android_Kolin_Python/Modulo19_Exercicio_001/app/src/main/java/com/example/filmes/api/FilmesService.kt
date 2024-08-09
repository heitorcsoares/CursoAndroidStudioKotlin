package com.example.filmes.api

import com.example.filmes.data.ApiCredentials
import com.example.filmes.data.FilmesDetails
import com.example.filmes.data.FilmesPosters
import com.example.filmes.data.FilmesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmesService {

    @GET("https://api.themoviedb.org/3/movie/now_playing")
    suspend fun getFilmesLista(
        @Query("api_key") apikey: String = ApiCredentials.API_KEY
    ) : Response<FilmesResponse>


    @GET("https://api.themoviedb.org/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ) : Response<FilmesDetails>


    @GET("https://api.themoviedb.org/3/movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ) : Response<FilmesPosters>

}