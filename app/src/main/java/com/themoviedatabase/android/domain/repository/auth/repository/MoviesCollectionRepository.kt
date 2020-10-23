package com.themoviedatabase.android.domain.repository.auth.repository

import com.themoviedatabase.android.domain.model.movies.MDBMovieCollection
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.flow.Flow

interface MoviesCollectionRepository {
    fun getRecentMoviesCollection(): Flow<MDBResult<List<MDBMovieCollection>>>
    fun getMoviesPopularCollection(): Flow<MDBResult<List<MDBMovieCollection>>>
    fun getMoviesUpComingCollection(): Flow<MDBResult<List<MDBMovieCollection>>>
}