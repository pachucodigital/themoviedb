package com.themoviedatabase.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MDBApiError(val status_code: Int,
                  val status_message: String)
