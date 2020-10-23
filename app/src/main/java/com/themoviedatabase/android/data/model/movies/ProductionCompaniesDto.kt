package com.themoviedatabase.android.data.model.movies

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompaniesDto(
    val id: Int,
    val logo_path: String?,
    val name: String,
    val origin_country: String
)