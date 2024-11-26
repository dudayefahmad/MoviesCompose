package com.ahmaddudayef.moviescompose.di

import com.ahmaddudayef.moviescompose.domain.repository.MovieRepository
import com.ahmaddudayef.moviescompose.domain.repository.TvShowRepository
import com.ahmaddudayef.moviescompose.domain.usecase.GetDetailMovieUseCase
import com.ahmaddudayef.moviescompose.domain.usecase.GetDetailTvShowUseCase
import com.ahmaddudayef.moviescompose.domain.usecase.GetMoviesUseCase
import com.ahmaddudayef.moviescompose.domain.usecase.GetTvShowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(
        repository: MovieRepository
    ): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetDetailMovieUseCase(
        repository: MovieRepository
    ): GetDetailMovieUseCase {
        return GetDetailMovieUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTvShowsUseCase(
        repository: TvShowRepository
    ): GetTvShowsUseCase {
        return GetTvShowsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetDetailTvShowUseCase(
        repository: TvShowRepository
    ): GetDetailTvShowUseCase {
        return GetDetailTvShowUseCase(repository)
    }
}