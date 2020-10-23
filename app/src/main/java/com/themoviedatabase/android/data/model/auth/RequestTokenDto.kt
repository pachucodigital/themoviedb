package com.themoviedatabase.android.data.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestTokenDto(@SerialName("success") val success: Boolean, @SerialName("expires_at") val expires_at: String, @SerialName("request_token") val request_token: String)