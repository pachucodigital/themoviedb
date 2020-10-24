package com.themoviedatabase.android.data.model.movies

import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguagesDto(
    val iso_639_1: String,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}