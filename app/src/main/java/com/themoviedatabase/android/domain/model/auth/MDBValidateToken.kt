package com.themoviedatabase.android.domain.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MDBValidateToken(val expiresAt: String, @SerialName("requestToken") val requestToken: String)