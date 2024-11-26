package com.ahmaddudayef.moviescompose.domain.repository

import com.ahmaddudayef.moviescompose.domain.Result
import com.ahmaddudayef.moviescompose.domain.model.DetailMovie
import com.ahmaddudayef.moviescompose.domain.model.DetailTvShow
import com.ahmaddudayef.moviescompose.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    suspend fun getTvShows(): Flow<Result<List<TvShow>>>
    suspend fun getDetailTvShow(id: Long): Flow<Result<DetailTvShow>>
}