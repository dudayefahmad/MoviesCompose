package com.ahmaddudayef.moviescompose.data.repository

import com.ahmaddudayef.moviescompose.data.api.TmdbApiService
import com.ahmaddudayef.moviescompose.data.dto.toDetailTvShow
import com.ahmaddudayef.moviescompose.data.dto.toListTvShow
import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.DetailTvShow
import com.ahmaddudayef.moviescompose.domain.model.TvShow
import com.ahmaddudayef.moviescompose.domain.repository.TvShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ITvShowRepository @Inject constructor(
    private val apiService: TmdbApiService
) : TvShowRepository {

    override suspend fun getTvShows(): Flow<Result<List<TvShow>>> = flow {
        try {
            val tvShowResponse = apiService.getTvShows()
            emit(Result.Success(tvShowResponse.tvShowDtos.toListTvShow()))
        } catch (e: Throwable) {
            emit(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getDetailTvShow(id: Long): Flow<Result<DetailTvShow>> = flow {
        try {
            val detailTvShowResponse = apiService.getDetailTvShow(id)
            emit(Result.Success(detailTvShowResponse.toDetailTvShow()))
        } catch (e: Throwable) {
            emit(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}