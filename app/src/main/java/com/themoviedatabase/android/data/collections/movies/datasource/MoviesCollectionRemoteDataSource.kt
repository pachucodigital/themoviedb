package com.themoviedatabase.android.data.collections.movies.datasource

import com.themoviedatabase.android.BuildConfig
import com.themoviedatabase.android.data.api.collections.movies.MoviesCollectionApiService
import com.themoviedatabase.android.data.model.movies.MoviesCollectionDto
import com.themoviedatabase.android.data.model.movies.MovieDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesCollectionRemoteDataSource @Inject constructor(private val movieApiService: MoviesCollectionApiService): MoviesCollectionDataSource {
    override fun getRecentCollection(): Flow<MovieDto> {
        return flow {
            val result = movieApiService.getRecentCollection(BuildConfig.API_KEY)
            emit(result)
        }
    }

    override fun getPopularCollection(): Flow<List<MoviesCollectionDto>> {
        return flow {
            val result = movieApiService.getPopularCollection(BuildConfig.API_KEY)
            emit(result.results)
        }
    }

    override fun getUpcomingCollection(): Flow<List<MoviesCollectionDto>> {
        return flow {
            val result = movieApiService.getUpcomingCollection(BuildConfig.API_KEY)
            emit(result.results)
        }
    }
}