package com.themoviedatabase.android.di.collection.movies

import com.themoviedatabase.android.data.collections.movies.MoviesCollectionRepositoryImp
import com.themoviedatabase.android.data.collections.movies.datasource.MoviesCollectionDataSource
import com.themoviedatabase.android.data.collections.movies.datasource.MoviesCollectionDataSourceImp
import com.themoviedatabase.android.domain.repository.auth.repository.MoviesCollectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
abstract class MoviesCollectionHiltModule {

    @Binds
    @Singleton
    abstract fun provideMoviesCollectionDataSource(impl: MoviesCollectionDataSourceImp): MoviesCollectionDataSource

    @Binds
    @Singleton
    abstract fun provideMoviesCollectionRepository(impl: MoviesCollectionRepositoryImp): MoviesCollectionRepository
}