package com.ahmaddudayef.moviescompose.di

import com.ahmaddudayef.moviescompose.data.Constants
import com.ahmaddudayef.moviescompose.data.api.TmdbApiService
import com.ahmaddudayef.moviescompose.data.repository.IMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideTmdbApiService(): TmdbApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TmdbApiService::class.java)
    }

    @Provides
    fun provideMovieRepository(apiService: TmdbApiService): IMovieRepository {
        return IMovieRepository(apiService)
    }


}