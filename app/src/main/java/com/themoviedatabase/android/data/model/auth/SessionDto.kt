package com.themoviedatabase.android.data.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SessionDto(@SerialName("success") val success: Boolean, @SerialName("session_id") val session_id: String)
