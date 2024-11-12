package com.ahmaddudayef.moviescompose.domain.repository

import com.ahmaddudayef.moviescompose.domain.model.Movie
import com.ahmaddudayef.moviescompose.domain.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<Result<List<Movie>>>
}