package com.themoviedatabase.android.domain.repository.collections

import com.themoviedatabase.android.domain.model.colletions.MDBCollection
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun getRecentMoviesCollection(): Flow<MDBResult<List<MDBCollection>>>
    fun getMoviesPopularCollection(): Flow<MDBResult<List<MDBCollection>>>
    fun getMoviesUpComingCollection(): Flow<MDBResult<List<MDBCollection>>>


}