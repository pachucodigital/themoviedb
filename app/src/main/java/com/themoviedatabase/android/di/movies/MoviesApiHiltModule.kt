package com.themoviedatabase.android.di.movies

import com.themoviedatabase.android.data.api.movies.MoviesApiService
import com.themoviedatabase.android.di.clients.DefaultRetrofitV3
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object MoviesApiHiltModule {

    @Provides
    @Singleton
    fun provideMoviesApiService(@DefaultRetrofitV3 retrofit: Retrofit): MoviesApiService {
        return retrofit.create(MoviesApiService::class.java)
    }
}