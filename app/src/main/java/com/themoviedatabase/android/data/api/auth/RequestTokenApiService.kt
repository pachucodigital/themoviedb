package com.themoviedatabase.android.data.api.auth

import com.themoviedatabase.android.BuildConfig
import com.themoviedatabase.android.data.model.auth.RequestTokenDto
import com.themoviedatabase.android.data.model.auth.ValidateTokenRequestParam
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RequestTokenApiService {
    @GET("${BuildConfig.V3}/authentication/token/new")
    suspend fun requestToken(@Query("api_key") apiKey: String): RequestTokenDto

    @POST("${BuildConfig.V3}/authentication/token/validate_with_login")
    suspend fun validateToken(@Query("api_key") apiKey: String, @Body body: ValidateTokenRequestParam): RequestTokenDto
}