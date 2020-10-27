package com.themoviedatabase.android.domain.model.movies

import com.themoviedatabase.android.data.model.movies.GenreDto
import com.themoviedatabase.android.data.model.movies.ProductionCompaniesDto
import com.themoviedatabase.android.data.model.movies.ProductionCountriesDto
import com.themoviedatabase.android.data.model.movies.SpokenLanguagesDto


data class MDBMovie(
        val adult: Boolean,
        val belongs_to_collection: String? = null,
        val budget: Int? = null,
        val backdrop_path: String?,
        val genres: List<GenreDto>? = null,
        val homepage: String? = null,
        val id: Int,
        val imdb_id: String?,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String?,
        val production_companies: List<ProductionCompaniesDto>?,
        val production_countries: List<ProductionCountriesDto>?,
        val release_date: String,
        val revenue: Int,
        val runtime: Int,
        val spoken_languages: List<SpokenLanguagesDto>?,
        val status: String,
        val tagline: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
         val vote_count: Int
){
}
