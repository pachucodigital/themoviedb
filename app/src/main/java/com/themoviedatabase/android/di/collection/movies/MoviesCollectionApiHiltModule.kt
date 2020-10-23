package com.themoviedatabase.android.di.collection.movies

import com.themoviedatabase.android.data.api.collections.movies.MoviesCollectionApiService
import com.themoviedatabase.android.di.clients.DefaultRetrofitV3
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object MoviesCollectionApiHiltModule {

    @Provides
    @Singleton
    fun provideMoviesCollectionApiService(@DefaultRetrofitV3 retrofit: Retrofit): MoviesCollectionApiService {
        return retrofit.create(MoviesCollectionApiService::class.java)
    }
}