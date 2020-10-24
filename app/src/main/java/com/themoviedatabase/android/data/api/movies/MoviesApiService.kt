package com.themoviedatabase.android.data.api.movies

import com.themoviedatabase.android.BuildConfig
import com.themoviedatabase.android.data.model.movies.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    @GET("${BuildConfig.V3}/movie/{movie_id}")
    suspend fun getRecentCollection( @Path("movie_id") id: Int, @Query("api_key") apiKey: String): MovieDto

}