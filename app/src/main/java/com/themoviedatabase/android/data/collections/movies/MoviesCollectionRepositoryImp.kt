package com.themoviedatabase.android.data.collections.movies

import android.util.Log
import com.themoviedatabase.android.data.collections.movies.datasource.MapperMovieCollection
import com.themoviedatabase.android.data.collections.movies.datasource.MoviesCollectionDataSource
import com.themoviedatabase.android.domain.model.colletions.MDBCollection
import com.themoviedatabase.android.domain.repository.collections.CollectionRepository
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MoviesCollectionRepositoryImp @Inject constructor(private val dataSourceImp: MoviesCollectionDataSource ,
                                                        private val mapperCollections: MapperMovieCollection,
                                                        ) : CollectionRepository {

    @ExperimentalCoroutinesApi
    override fun getRecentMoviesCollection(): Flow<MDBResult<List<MDBCollection>>> {
        return flow {
           dataSourceImp.getRecentCollection().onStart {
               emit(MDBResult.Loading)
           }.catch {
               Log.e("Recents", "$it")
           }.map {
               arrayListOf( mapperCollections.dtoToDomain(it))

           }.collect {
               emit(MDBResult.Success(it))
           }
        }
    }

    @ExperimentalCoroutinesApi
    override fun getMoviesPopularCollection(): Flow<MDBResult<List<MDBCollection>>> {
        return flow {
            dataSourceImp.getPopularCollection().onStart {
                emit(MDBResult.Loading)
            }.map {
                it.map {item->
                    mapperCollections.dtoToDomain(item)
                }
            }.collect {
                emit(MDBResult.Success(it))
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun getMoviesUpComingCollection(): Flow<MDBResult<List<MDBCollection>>> {
        return flow {
            dataSourceImp.getUpcomingCollection().onStart {
                emit(MDBResult.Loading)
            }.map {
                it.map {item->
                    mapperCollections.dtoToDomain(item)
                }
            }.collect {
                emit(MDBResult.Success(it))
            }
        }
    }
}