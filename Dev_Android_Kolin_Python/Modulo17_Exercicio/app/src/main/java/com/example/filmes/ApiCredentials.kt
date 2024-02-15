package com.example.filmes

class ApiCredentials {

    val api_key = ecdd57b00e263182164add9d75414318

    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://api.themoviedb.org/3/movie/latest")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlY2RkNTdiMDBlMjYzMTgyMTY0YWRkOWQ3NTQxNDMxOCIsInN1YiI6IjY1YjdlZDM0MGZiMTdmMDE2MjM0MWQyNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JLwWPS-2WH22bQzDa8mN83wXt_IIdSC_4VCp38bnj9M")
        .build()

    val response = client.newCall(request).execute()
}