package com.themoviedatabase.android.data.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class SigningRequestParams(@SerialName("user") val user: String, @SerialName("password") val password: String)