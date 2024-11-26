package com.ahmaddudayef.moviescompose.domain.usecase

import com.ahmaddudayef.moviescompose.domain.repository.TvShowRepository
import javax.inject.Inject

class GetDetailTvShowUseCase @Inject constructor(private val tvShowRepository: TvShowRepository) {
    suspend operator fun invoke(id: Long) = tvShowRepository.getDetailTvShow(id)
}