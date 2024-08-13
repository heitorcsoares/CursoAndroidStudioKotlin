package com.example.hqawasomeapp.api

import com.example.hqawasomeapp.data.ComicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicService {

    @GET("/v1/public/comics")
    suspend fun getComicList(
        @Query("ts") timeStamp: String,
        @Query("apikey") publickey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ) : Response<ComicResponse>

}