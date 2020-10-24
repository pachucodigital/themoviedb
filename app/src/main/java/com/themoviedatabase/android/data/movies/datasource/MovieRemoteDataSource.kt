package com.themoviedatabase.android.data.movies.datasource

import com.themoviedatabase.android.BuildConfig
import com.themoviedatabase.android.data.api.movies.MoviesApiService
import com.themoviedatabase.android.data.model.movies.MovieDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val apiService: MoviesApiService) : MovieDataSource {
    override fun getDetailMovie(id: Int): Flow<MovieDto> {
        return flow {
            emit(apiService.getRecentCollection(id, BuildConfig.API_KEY))
        }
    }
}