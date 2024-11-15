package com.ahmaddudayef.moviescompose.data.api

import com.ahmaddudayef.moviescompose.data.dto.DetailMovieResponse
import com.ahmaddudayef.moviescompose.data.dto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApiService {

    @GET("discover/movie")
    suspend fun getMovies(): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movieId: Long): DetailMovieResponse
}