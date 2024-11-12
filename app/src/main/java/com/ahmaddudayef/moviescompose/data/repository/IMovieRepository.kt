package com.ahmaddudayef.moviescompose.data.repository

import com.ahmaddudayef.moviescompose.data.Constants
import com.ahmaddudayef.moviescompose.data.api.TmdbApiService
import com.ahmaddudayef.moviescompose.data.dto.toListMovie
import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.Movie
import com.ahmaddudayef.moviescompose.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class IMovieRepository @Inject constructor(
    private val apiService: TmdbApiService
) : MovieRepository {

    private val apiKey = Constants.API_KEY

    override suspend fun getMovies(): Flow<Result<List<Movie>>> = flow {
        try {
            val movies = apiService.getMovies(apiKey = apiKey)
            emit(Result.Success(movies.results.toListMovie()))
        } catch (e: Throwable) {
            emit(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)

}