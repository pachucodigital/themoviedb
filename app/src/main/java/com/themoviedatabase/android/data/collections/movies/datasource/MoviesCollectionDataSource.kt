package com.themoviedatabase.android.data.collections.movies.datasource

import com.themoviedatabase.android.data.model.movies.MoviesCollectionDto
import com.themoviedatabase.android.data.model.movies.MovieDto
import kotlinx.coroutines.flow.Flow

interface MoviesCollectionDataSource {
    fun getRecentCollection(): Flow<MovieDto>
    fun getPopularCollection(): Flow<List<MoviesCollectionDto>>
    fun getUpcomingCollection(): Flow<List<MoviesCollectionDto>>

}