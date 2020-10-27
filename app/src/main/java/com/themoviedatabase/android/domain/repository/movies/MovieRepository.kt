package com.themoviedatabase.android.domain.repository.movies

import com.themoviedatabase.android.domain.model.movies.MDBMovie
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getDetailMovie(id: Int): Flow<MDBResult<MDBMovie>>
}