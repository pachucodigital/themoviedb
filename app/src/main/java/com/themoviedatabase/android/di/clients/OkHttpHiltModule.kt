package com.themoviedatabase.android.di.clients

import com.themoviedatabase.android.data.api.ConfigurationClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@InstallIn(ApplicationComponent::class)
@Module
object OkHttpHiltModule {
    @Provides
    @DefaultOkHttpClient
    fun provideDefaultClient(@DefaultConfigurationClient configurationClient: ConfigurationClient): OkHttpClient{
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(configurationClient.readTimeOut, configurationClient.timeUnit)
        builder.connectTimeout(configurationClient.connectTimeOut, configurationClient.timeUnit)
        if(configurationClient.listInterceptor.isNotEmpty()) {
            for(interceptor: Interceptor in configurationClient.listInterceptor) {
                builder.addInterceptor(interceptor)
            }
        }
        return builder.build()
    }
}


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultOkHttpClient