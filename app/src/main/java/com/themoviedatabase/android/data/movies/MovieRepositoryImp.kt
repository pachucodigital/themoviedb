package com.themoviedatabase.android.data.movies

import android.util.Log
import com.themoviedatabase.android.data.movies.datasource.MovieRemoteDataSource
import com.themoviedatabase.android.domain.model.movies.MDBMovie
import com.themoviedatabase.android.domain.repository.movies.MovieRepository
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private  val dataSource: MovieRemoteDataSource): MovieRepository {
    @ExperimentalCoroutinesApi
    override fun getDetailMovie(id: Int): Flow<MDBResult<MDBMovie>> {
        return flow {
            dataSource.getDetailMovie(id).onStart {
                emit(MDBResult.Loading)
            }.catch {
                Log.e("MovieRepositoryImp", "getDetailMovie $it")
            }.map {
                MDBMovie(it.adult,
                it.belongs_to_collection,
                it.budget,
                it.backdrop_path,
                it.genres,
                it.homepage,
                it.id,
                it.imdb_id,
                it.original_language,
                it.original_title,
                it.overview,
                it.popularity,
                it.poster_path,
                it.production_companies,
                it.production_countries,
                it.release_date,
                it.revenue,
                it.runtime,
                it.spoken_languages,
                it.status,
                it.tagline,
                it.title,
                it.video,
                it.vote_average,
                it.vote_count)

            }.collect {
                emit(MDBResult.Success(it))
            }
        }
    }
}