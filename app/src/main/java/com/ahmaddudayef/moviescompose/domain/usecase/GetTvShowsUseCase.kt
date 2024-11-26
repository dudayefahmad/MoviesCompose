package com.ahmaddudayef.moviescompose.domain.usecase

import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.TvShow
import com.ahmaddudayef.moviescompose.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTvShowsUseCase @Inject constructor(private val tvShowRepository: TvShowRepository) {
    suspend operator fun invoke(): Flow<Result<List<TvShow>>> {
        return tvShowRepository.getTvShows()
    }
}