package com.themoviedatabase.android.di.auth.requesttoken

import com.themoviedatabase.android.data.api.auth.RequestTokenApi
import com.themoviedatabase.android.di.clients.DefaultRetrofitV3
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@InstallIn(ApplicationComponent::class)
@Module
object RequestTokenApiServiceHiltModule {

    @Provides
    fun provideRequestTokenApiService(@DefaultRetrofitV3 retrofit: Retrofit): RequestTokenApi {
        return retrofit.create(RequestTokenApi::class.java)
    }
}