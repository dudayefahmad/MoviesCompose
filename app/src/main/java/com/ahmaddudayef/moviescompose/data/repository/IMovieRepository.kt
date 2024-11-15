package com.ahmaddudayef.moviescompose.data.repository

import com.ahmaddudayef.moviescompose.data.api.TmdbApiService
import com.ahmaddudayef.moviescompose.data.dto.toDetailMovie
import com.ahmaddudayef.moviescompose.data.dto.toListMovie
import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.DetailMovie
import com.ahmaddudayef.moviescompose.domain.model.Movie
import com.ahmaddudayef.moviescompose.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IMovieRepository @Inject constructor(
    private val apiService: TmdbApiService
) : MovieRepository {

    override suspend fun getMovies(): Flow<Result<List<Movie>>> = flow {
        try {
            val moviesResponse = apiService.getMovies()
            emit(Result.Success(moviesResponse.results.toListMovie()))
        } catch (e: Throwable) {
            emit(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getDetailMovie(id: Long): Flow<Result<DetailMovie>> = flow {
        try {
            val detailMovieResponse = apiService.getDetailMovie(id)
            emit(Result.Success(detailMovieResponse.toDetailMovie()))
        } catch (e: Throwable) {
            emit(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)

}