package com.themoviedatabase.android.domain.model.auth

import kotlinx.serialization.Serializable

@Serializable
class MDBRequestToken(val expiresAt: String, val requestToken: String)