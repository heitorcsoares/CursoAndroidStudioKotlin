package com.example.hqawasomeapp

import android.icu.text.CaseMap.Title
import android.telecom.Call
import retrofit2.http.Query

interface HQservicos {

    @GET("/movie/latest")
    fun getHQ(@Query("id") id: String,@Query("title") title: String): Call<movie>

    @PUT() ("/movie/{movie_id}")
    fun updataHQdetalhe(): Call<Data>

    @PUT() ("/movie/{movie_id}/images")
    fun updataHQimagem(): Call<Data>

}