package com.example.filmes

import okhttp3.OkHttpClient
import okhttp3.Request

class ApiHelper {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://api.themoviedb.org/3/authentication")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMmI1MDEwYjAzNTJlYmQ4NzQ3NTNkNDBmMDk1YTExOCIsInN1YiI6IjY1YjdlZDM0MGZiMTdmMDE2MjM0MWQyNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.B-G3eNi0__LzJPsBZb1etlwY8qopdxiyTXadbjbAy0Q")
        .build()

    val response = client.newCall(request).execute()
}