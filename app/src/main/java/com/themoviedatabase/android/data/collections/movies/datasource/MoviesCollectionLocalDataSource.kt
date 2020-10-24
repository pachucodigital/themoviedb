package com.themoviedatabase.android.data.collections.movies.datasource

import com.themoviedatabase.android.data.db.dao.CollectionsDao
import com.themoviedatabase.android.data.db.entity.CollectionEntity
import com.themoviedatabase.android.data.model.movies.MovieDto
import com.themoviedatabase.android.data.model.movies.MoviesCollectionDto
import com.themoviedatabase.android.domain.model.colletions.MDBCollectionCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class MoviesCollectionLocalDataSource @Inject constructor(private val collectionsDao: CollectionsDao): MoviesCollectionDataSource {

    override fun getRecentCollection(): Flow<MovieDto?> {
        return collectionsDao.getAllByIdCategory(MDBCollectionCategory.Latest.toString()).map {
            if(it.isNotEmpty()) {
                Json.decodeFromString<MovieDto>(it.first().data)
            } else {
                null
            }

        }
    }

    override fun getPopularCollection(): Flow<List<MoviesCollectionDto>> {
        return collectionsDao.getAllByIdCategory(MDBCollectionCategory.Popular.toString()).map {
            val list = mutableListOf<MoviesCollectionDto>()
            it.forEach {
                list.add(Json.decodeFromString(it.data))
            }
            list
        }
    }

    override fun getUpcomingCollection(): Flow<List<MoviesCollectionDto>> {
        return collectionsDao.getAllByIdCategory(MDBCollectionCategory.UpComing.toString()).map  {
            val list = mutableListOf<MoviesCollectionDto>()
            it.forEach {
                list.add(Json.decodeFromString(it.data))
            }
            list
        }
    }

    override suspend fun saveRecentCollection(list: List<MovieDto>) {
        val recentList= arrayListOf<CollectionEntity>()
        for(movie: MovieDto in list){
            recentList.add(CollectionEntity(movie.id, MDBCollectionCategory.Latest.toString(), Json.encodeToString(movie)))
        }
        collectionsDao.refreshCollection(MDBCollectionCategory.Popular.toString(), recentList)
    }

    override suspend fun savePopularCollection(list: List<MoviesCollectionDto>) {
        val popularList= arrayListOf<CollectionEntity>()
        for(movie: MoviesCollectionDto in list){
            popularList.add(CollectionEntity(movie.id, MDBCollectionCategory.Popular.toString(), Json.encodeToString(movie)))
        }
        collectionsDao.refreshCollection(MDBCollectionCategory.Popular.toString(), popularList)
    }

    override suspend fun saveUpomingCollection(list: List<MoviesCollectionDto>) {
        val upcoming= arrayListOf<CollectionEntity>()
        for(movie: MoviesCollectionDto in list){
            upcoming.add(CollectionEntity(movie.id, MDBCollectionCategory.UpComing.toString(), Json.encodeToString(movie)))
        }
        collectionsDao.refreshCollection(MDBCollectionCategory.UpComing.toString(), upcoming)
    }
}