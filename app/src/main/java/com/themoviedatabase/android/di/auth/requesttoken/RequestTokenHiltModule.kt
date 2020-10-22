package com.themoviedatabase.android.di.auth.requesttoken

import com.themoviedatabase.android.data.auth.requesttoken.RequestTokenRepositoryImp
import com.themoviedatabase.android.data.auth.requesttoken.datasource.RequestTokenApiDataSource
import com.themoviedatabase.android.data.auth.requesttoken.datasource.RequestTokenApiDataSourceImp
import com.themoviedatabase.android.domain.repository.auth.RequestTokenRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RequestTokenHiltModule {

    @Binds
    abstract fun provideRequestTokenDataSource(impl: RequestTokenApiDataSourceImp): RequestTokenApiDataSource

    @Binds
    abstract fun provideRequestTokenRepository(impl: RequestTokenRepositoryImp): RequestTokenRepository
}