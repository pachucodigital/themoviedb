package com.themoviedatabase.android.di.clients

import com.themoviedatabase.android.data.api.ConfigurationClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@InstallIn(ApplicationComponent::class)
@Module
object ConfigurationHiltModule {
    @Provides
    @DefaultConfigurationClient
    fun provideDefaultConfiguration(): ConfigurationClient {
        return ConfigurationClient(60, 60, TimeUnit.SECONDS, InterceptorModule.provideDefaultInterceptors())
    }
}

@Retention
@Qualifier
annotation class DefaultConfigurationClient
