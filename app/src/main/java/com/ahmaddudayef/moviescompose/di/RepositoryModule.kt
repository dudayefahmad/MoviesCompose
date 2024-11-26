package com.ahmaddudayef.moviescompose.di

import com.ahmaddudayef.moviescompose.data.api.TmdbApiService
import com.ahmaddudayef.moviescompose.data.repository.IMovieRepository
import com.ahmaddudayef.moviescompose.data.repository.ITvShowRepository
import com.ahmaddudayef.moviescompose.domain.repository.MovieRepository
import com.ahmaddudayef.moviescompose.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: TmdbApiService): MovieRepository {
        return IMovieRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideTvShowRepository(apiService: TmdbApiService): TvShowRepository {
        return ITvShowRepository(apiService)
    }
}