package com.themoviedatabase.android.data.api.collections.movies

import com.themoviedatabase.android.BuildConfig
import com.themoviedatabase.android.data.model.base.BaseResponse
import com.themoviedatabase.android.data.model.movies.MoviesCollectionDto
import com.themoviedatabase.android.data.model.movies.MovieDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesCollectionApiService {

    @GET("${BuildConfig.V3}/movie/latest")
    suspend fun getRecentCollection(@Query("api_key") apiKey: String): MovieDto

    @GET("${BuildConfig.V3}/movie/popular")
    suspend fun getPopularCollection(@Query("api_key") apiKey: String): BaseResponse<MoviesCollectionDto>

    @GET("${BuildConfig.V3}/movie/upcoming")
    suspend fun getUpcomingCollection(@Query("api_key") apiKey: String): BaseResponse<MoviesCollectionDto>

}