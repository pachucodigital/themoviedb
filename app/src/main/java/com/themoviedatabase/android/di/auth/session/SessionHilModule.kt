package com.themoviedatabase.android.di.auth.session

import com.themoviedatabase.android.data.auth.session.SessionRepositoryImp
import com.themoviedatabase.android.data.auth.session.datasource.SessionDataSource
import com.themoviedatabase.android.data.auth.session.datasource.SessionDataSourceImp
import com.themoviedatabase.android.domain.repository.auth.session.SessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class SessionHilModule {

    @Binds
    @Singleton
    abstract fun provideSessionDataSource(impl: SessionDataSourceImp): SessionDataSource

    @Binds
    @Singleton
    abstract fun provideSessionRepository(impl: SessionRepositoryImp): SessionRepository
}