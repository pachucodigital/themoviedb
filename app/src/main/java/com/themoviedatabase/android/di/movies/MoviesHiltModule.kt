package com.themoviedatabase.android.di.movies

import com.themoviedatabase.android.data.movies.MovieRepositoryImp
import com.themoviedatabase.android.data.movies.datasource.MovieDataSource
import com.themoviedatabase.android.data.movies.datasource.MovieRemoteDataSource
import com.themoviedatabase.android.domain.repository.movies.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
abstract class MoviesHiltModule {
    @Binds
    @Singleton
    abstract fun provideMoviesRemoteDataSource(impl: MovieRemoteDataSource): MovieDataSource

    @Binds
    @Singleton
    abstract fun provideMoviesRepository(impl: MovieRepositoryImp): MovieRepository
}
