package com.themoviedatabase.android.data.model.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
        @SerialName("adult") val adult: Boolean,
        @SerialName("belongs_to_collection") val belongs_to_collection: String? = null,
        @SerialName("budget") val budget: Int? = null,
        @SerialName("backdrop_path") val backdrop_path: String?,
        @SerialName("genres") val genres: List<GenreDto>? = null,
        @SerialName("homepage") val homepage: String? = null,

        @SerialName("id") val id: Int,
        @SerialName("imdb_id") val imdb_id: String?,
        @SerialName("original_language") val original_language: String,
        @SerialName("original_title") val original_title: String,
        @SerialName("overview") val overview: String,
        @SerialName("popularity") val popularity: Double,
        @SerialName("poster_path") val poster_path: String?,
        @SerialName("production_companies") val production_companies: List<ProductionCompaniesDto>? ,
        @SerialName("production_countries") val production_countries: List<ProductionCountriesDto>? ,
        @SerialName("release_date") val release_date: String,
        @SerialName("revenue") val revenue: Int,
        @SerialName("runtime") val runtime: Int,
        @SerialName("spoken_languages") val spoken_languages: List<String>? ,
        @SerialName("status") val status: String,
        @SerialName("tagline") val tagline: String,
        @SerialName("title") val title: String,
        @SerialName("video") val video: Boolean,
        @SerialName("vote_average") val vote_average: Double,
        @SerialName("vote_count") val vote_count: Int
)