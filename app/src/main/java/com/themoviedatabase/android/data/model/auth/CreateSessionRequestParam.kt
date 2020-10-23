package com.themoviedatabase.android.data.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CreateSessionRequestParam(@SerialName("request_token") private val request_token: String)