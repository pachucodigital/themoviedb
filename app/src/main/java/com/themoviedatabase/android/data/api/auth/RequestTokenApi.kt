package com.themoviedatabase.android.data.api.auth

import com.themoviedatabase.android.BuildConfig
import com.themoviedatabase.android.data.model.auth.RequestTokenDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestTokenApi {
    @GET("${BuildConfig.V3}/authentication/token/new")
    suspend fun requestToken(@Query("api_key") apiKey: String): RequestTokenDto
}