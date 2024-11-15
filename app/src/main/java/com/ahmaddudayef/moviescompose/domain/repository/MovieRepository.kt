package com.ahmaddudayef.moviescompose.domain.repository

import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.DetailMovie
import com.ahmaddudayef.moviescompose.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<Result<List<Movie>>>
    suspend fun getDetailMovie(id: Long): Flow<Result<DetailMovie>>
}