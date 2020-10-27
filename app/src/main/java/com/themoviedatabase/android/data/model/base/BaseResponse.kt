package com.themoviedatabase.android.data.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BaseResponse<T>(
        @SerialName("page") val page: Int,
        @SerialName("total_results") val total_results: Int,
        @SerialName("total_pages") val total_pages: Int,
        @SerialName("dates") val dates: Dates? = null,
        @SerialName("results")  val results: List<T>
)

