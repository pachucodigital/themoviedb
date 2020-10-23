package com.themoviedatabase.android.di.auth.session

import com.themoviedatabase.android.data.api.auth.SessionApiService
import com.themoviedatabase.android.di.clients.DefaultRetrofitV3
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object SessionApiServiceHiltModule {

    @Provides
    @Singleton
    fun provideRequestTokenApiService(@DefaultRetrofitV3 retrofit: Retrofit): SessionApiService {
        return retrofit.create(SessionApiService::class.java)
    }
}