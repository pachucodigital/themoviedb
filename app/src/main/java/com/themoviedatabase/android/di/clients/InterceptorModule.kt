package com.themoviedatabase.android.di.clients

import com.themoviedatabase.android.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Qualifier


object InterceptorModule {

    fun provideDefaultInterceptors(): List<Interceptor> {
        return arrayListOf(provideLoggerInterceptor())
    }

    fun provideLoggerInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }
}
