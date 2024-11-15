package com.ahmaddudayef.moviescompose.domain.usecase

import com.ahmaddudayef.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(id: Long) = movieRepository.getDetailMovie(id)
}