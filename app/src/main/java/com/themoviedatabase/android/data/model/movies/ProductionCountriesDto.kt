package com.themoviedatabase.android.data.model.movies

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountriesDto(
    val iso_3166_1: String,
    val name: String
)