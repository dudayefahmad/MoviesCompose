package com.ahmaddudayef.moviescompose.data.api

import com.ahmaddudayef.moviescompose.data.dto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
    ): MovieResponse
}