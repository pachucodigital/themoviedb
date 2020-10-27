package com.themoviedatabase.android.data.movies.datasource

import com.themoviedatabase.android.data.model.movies.MovieDto
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    fun getDetailMovie(id: Int): Flow<MovieDto>
}