package com.example.filmes.api

import okhttp3.OkHttpClient
import okhttp3.Request

class ApiCliente {

    val client = OkHttpClient()

    val request = Request.Builder()
        .url(ApiCredentials.AUTHENTICATION)
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMmI1MDEwYjAzNTJlYmQ4NzQ3NTNkNDBmMDk1YTExOCIsInN1YiI6IjY1YjdlZDM0MGZiMTdmMDE2MjM0MWQyNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.B-G3eNi0__LzJPsBZb1etlwY8qopdxiyTXadbjbAy0Q")
        .build()

    val response = client.newCall(request).execute()

}


/** https://developer.themoviedb.org/reference/intro/getting-started  */