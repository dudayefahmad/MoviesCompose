package com.ahmaddudayef.moviescompose.data.api

import com.ahmaddudayef.moviescompose.data.dto.MovieResponse
import retrofit2.http.GET

interface TmdbApiService {

    @GET("discover/movie")
    suspend fun getMovies(): MovieResponse
}