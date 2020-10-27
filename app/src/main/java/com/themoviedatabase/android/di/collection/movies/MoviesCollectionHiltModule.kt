package com.themoviedatabase.android.di.collection.movies

import com.themoviedatabase.android.data.collections.movies.MoviesCollectionRepositoryImp
import com.themoviedatabase.android.data.collections.movies.datasource.MoviesCollectionDataSource
import com.themoviedatabase.android.data.collections.movies.datasource.MoviesCollectionLocalDataSource
import com.themoviedatabase.android.data.collections.movies.datasource.MoviesCollectionRemoteDataSource
import com.themoviedatabase.android.domain.repository.collections.CollectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
abstract class MoviesCollectionHiltModule {

    @Binds
    @Singleton
    @CollectionRemoteDataSource
    abstract fun provideMoviesCollectioRemotenDataSource(impl: MoviesCollectionRemoteDataSource): MoviesCollectionDataSource


    @Binds
    @Singleton
    @CollectionLocalDataSource
    abstract fun provideMoviesCollectionLocalDataSource(impl: MoviesCollectionLocalDataSource): MoviesCollectionDataSource


    @Binds
    @Singleton
    abstract fun provideMoviesCollectionRepository(impl: MoviesCollectionRepositoryImp): CollectionRepository
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CollectionLocalDataSource

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CollectionRemoteDataSource