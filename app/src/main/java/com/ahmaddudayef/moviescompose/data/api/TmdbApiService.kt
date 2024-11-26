package com.ahmaddudayef.moviescompose.data.api

import com.ahmaddudayef.moviescompose.data.dto.DetailMovieResponse
import com.ahmaddudayef.moviescompose.data.dto.DetailTvShowResponse
import com.ahmaddudayef.moviescompose.data.dto.MovieResponse
import com.ahmaddudayef.moviescompose.data.dto.TvShowResponse
import com.ahmaddudayef.moviescompose.domain.model.DetailTvShow
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApiService {

    @GET("discover/movie")
    suspend fun getMovies(): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movieId: Long): DetailMovieResponse

    @GET("discover/tv")
    suspend fun getTvShows(): TvShowResponse

    @GET("tv/{series_id}")
    suspend fun getDetailTvShow(@Path("series_id") seriesId: Long): DetailTvShowResponse
}