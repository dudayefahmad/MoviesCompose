package com.ahmaddudayef.moviescompose.domain.usecase

import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.Movie
import com.ahmaddudayef.moviescompose.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Flow<Result<List<Movie>>> {
        return movieRepository.getMovies()
    }
}