package com.ahmaddudayef.moviescompose.di

import com.ahmaddudayef.moviescompose.domain.repository.MovieRepository
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
    fun provideGetProductsUseCase(
        repository: MovieRepository
    ): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }
}