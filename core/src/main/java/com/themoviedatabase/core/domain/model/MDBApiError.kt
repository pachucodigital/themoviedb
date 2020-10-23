package com.themoviedatabase.core.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MDBApiError(val success: Boolean? = null,
                       @SerialName("status_code") val status_code: Int,
                       @SerialName("status_message") val status_message: String)
