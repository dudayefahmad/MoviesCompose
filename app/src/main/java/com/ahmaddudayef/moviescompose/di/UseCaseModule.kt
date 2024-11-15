package com.ahmaddudayef.moviescompose.di

import com.ahmaddudayef.moviescompose.domain.repository.MovieRepository
import com.ahmaddudayef.moviescompose.domain.usecase.GetDetailMovieUseCase
import com.ahmaddudayef.moviescompose.domain.usecase.GetMoviesUseCase
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
}