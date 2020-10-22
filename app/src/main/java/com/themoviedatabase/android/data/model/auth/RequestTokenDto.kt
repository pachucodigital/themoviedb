package com.themoviedatabase.android.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class RequestTokenDto(val success: Boolean, val expires_at: String, val request_token: String)