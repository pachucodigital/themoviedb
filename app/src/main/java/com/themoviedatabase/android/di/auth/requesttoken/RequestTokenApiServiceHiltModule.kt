package com.themoviedatabase.android.di.auth.requesttoken

import com.themoviedatabase.android.data.api.auth.RequestTokenApiService
import com.themoviedatabase.android.di.clients.DefaultRetrofitV3
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RequestTokenApiServiceHiltModule {

    @Provides
    @Singleton
    fun provideRequestTokenApiService(@DefaultRetrofitV3 retrofit: Retrofit): RequestTokenApiService {
        return retrofit.create(RequestTokenApiService::class.java)
    }
}