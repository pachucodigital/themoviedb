package com.themoviedatabase.android.data.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class ValidateTokenRequestParam(@SerialName("request_token") private val request_token: String, @SerialName("username") private val username: String, @SerialName("password") private val  password: String)