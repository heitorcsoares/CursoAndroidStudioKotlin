package com.example.hqawasomeapp.hqHome

import retrofit2.http.GET
import retrofit2.http.Query

interface ComicService {

    @GET("/V1/public/comics")
    fun getComicList(
        @Query("ts") timeStamp: String,
        @Query("apikey") publickey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    )

}