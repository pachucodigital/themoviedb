package com.themoviedatabase.android.data.collections.movies

import com.themoviedatabase.android.data.collections.movies.datasource.MapperMovieCollection
import com.themoviedatabase.android.data.collections.movies.datasource.MoviesCollectionDataSource
import com.themoviedatabase.android.data.model.movies.MoviesCollectionDto
import com.themoviedatabase.android.di.collection.movies.CollectionLocalDataSource
import com.themoviedatabase.android.di.collection.movies.CollectionRemoteDataSource
import com.themoviedatabase.android.domain.model.colletions.MDBCollection
import com.themoviedatabase.android.domain.repository.collections.CollectionRepository
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.net.UnknownHostException
import javax.inject.Inject

class MoviesCollectionRepositoryImp @Inject constructor(@CollectionRemoteDataSource private val remoteDataSource: MoviesCollectionDataSource,
                                                        @CollectionLocalDataSource private val localDataSource: MoviesCollectionDataSource,
                                                        private val mapperCollections: MapperMovieCollection,
                                                        ) : CollectionRepository {

    @ExperimentalCoroutinesApi
    override fun getRecentMoviesCollection(): Flow<MDBResult<List<MDBCollection>>> {
        return flow {
           localDataSource.getRecentCollection().map {
               if(it != null) {
                   arrayListOf(mapperCollections.dtoToDomain(it))
               } else {
                   arrayListOf()
               }
           }.collect {
               if(it.isNotEmpty()){
                   emit(MDBResult.Success(it))
                   remoteDataSource.getRecentCollection().catch {
                       // Show Content From Local
                   }.map { movieDto ->
                       if(movieDto != null) {
                           localDataSource.saveRecentCollection(arrayListOf(movieDto))
                           arrayListOf(mapperCollections.dtoToDomain(movieDto))
                       } else {
                           arrayListOf()
                       }
                   }.collect {
                       emit(MDBResult.Success(it))
                   }
               } else {
                   remoteDataSource.getRecentCollection().catch {
                       emit(MDBResult.Error(it))
                   }.map { movieDto ->
                       if(movieDto != null) {
                           localDataSource.saveRecentCollection(arrayListOf(movieDto))
                           arrayListOf(mapperCollections.dtoToDomain(movieDto))
                       } else {
                           arrayListOf()
                       }
                   }.collect {
                       emit(MDBResult.Success(it))
                   }
               }
           }
        }

    }


    @ExperimentalCoroutinesApi
    override fun getMoviesPopularCollection(): Flow<MDBResult<List<MDBCollection>>> {
        return loadData(
            fetchFromLocal = { localDataSource.getPopularCollection() },
            shouldFetchFromRemote = {
                it.isEmpty()
            },
            fetchFromRemote = { remoteDataSource.getPopularCollection() },
            processRemoteResponse = { it.map { item -> mapperCollections.dtoToDomain(item) }},
            saveRemoteData = { localDataSource.savePopularCollection(it) }
        )
    }

    @ExperimentalCoroutinesApi
    override fun getMoviesUpComingCollection(): Flow<MDBResult<List<MDBCollection>>> {
        return loadData(
            fetchFromLocal = { localDataSource.getUpcomingCollection() },
            shouldFetchFromRemote = { it.isEmpty()},
            fetchFromRemote = { remoteDataSource.getUpcomingCollection() },
            processRemoteResponse = { it.map { item -> mapperCollections.dtoToDomain(item) }},
            saveRemoteData = { localDataSource.saveUpomingCollection(it) }
        )

    }

    inline fun loadData(
        crossinline fetchFromLocal: () -> Flow<List<MoviesCollectionDto>>,
        crossinline shouldFetchFromRemote: (List<MoviesCollectionDto>) -> Boolean = { true },
        crossinline fetchFromRemote: () -> Flow<List<MoviesCollectionDto>>,
        crossinline processRemoteResponse: (response: List<MoviesCollectionDto>) -> List<MDBCollection> = { arrayListOf()},
        crossinline saveRemoteData: suspend (List<MoviesCollectionDto>) -> Unit = { Unit }
    ) = flow {
        emit(MDBResult.Loading)
        val localData = fetchFromLocal().first()
        if (shouldFetchFromRemote(localData)) {
            emit(MDBResult.Loading)
            fetchFromRemote().map {
                saveRemoteData(it)
                processRemoteResponse(it)
            }.collect { apiResponse ->
                emit(MDBResult.Success(apiResponse))
            }
        } else {
            fetchFromLocal().map {
                processRemoteResponse(it)
            }.collect { localCollectionList ->
                emit(MDBResult.Success(localCollectionList))
                fetchFromRemote().catch {
                    if(localData.isEmpty()) { // Not Local information to show
                        emit(MDBResult.Error(it))
                    }
                }.map {
                    saveRemoteData(it)
                    processRemoteResponse(it)
                }.collect { apiResponse ->
                    emit(MDBResult.Success(apiResponse))
                }
            }
        }
    }
}