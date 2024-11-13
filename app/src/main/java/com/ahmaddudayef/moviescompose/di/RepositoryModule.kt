package com.ahmaddudayef.moviescompose.di

import com.ahmaddudayef.moviescompose.data.api.TmdbApiService
import com.ahmaddudayef.moviescompose.data.repository.IMovieRepository
import com.ahmaddudayef.moviescompose.domain.repository.MovieRepository
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
}