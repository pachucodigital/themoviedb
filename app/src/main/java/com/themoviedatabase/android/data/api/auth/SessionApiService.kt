package com.themoviedatabase.android.data.api.auth

import com.themoviedatabase.android.BuildConfig
import com.themoviedatabase.android.data.model.auth.CreateSessionRequestParam
import com.themoviedatabase.android.data.model.auth.SessionDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface SessionApiService {
    @POST("${BuildConfig.V3}/authentication/session/new")
    suspend fun createSession(@Query("api_key") api_key: String, @Body body: CreateSessionRequestParam): SessionDto
}