package com.themoviedatabase.android.di.clients

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.themoviedatabase.android.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Qualifier

@InstallIn(ApplicationComponent::class)
@Module
object RetrofitHiltModule {

    @Provides
    @DefaultRetrofitV3
    @ExperimentalSerializationApi
    fun provideRetrofit(@DefaultOkHttpClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultRetrofitV3