package com.themoviedatabase.android.data.db.dao

import android.util.Log
import androidx.room.*
import com.themoviedatabase.android.data.db.entity.CollectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionsDao {
    @Query("SELECT * FROM CollectionEntity")
    fun getAll(): Flow<List<CollectionEntity>>

    @Query("SELECT * FROM CollectionEntity WHERE collection_id IN (:categotyId)")
    fun getAllByIdCategory(categotyId: String): Flow<List<CollectionEntity>>

    @Query("DELETE FROM CollectionEntity WHERE collection_id == :categotyId")
    suspend fun deleteCollection(categotyId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(collections: List<CollectionEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(collections: CollectionEntity)

    @Delete
    suspend fun delete(collection: CollectionEntity)

    @Query("DELETE FROM CollectionEntity")
    suspend fun deleteAllCollection()

    @Transaction
    suspend fun refreshCollection(id: String, collections: List<CollectionEntity>) {
        deleteCollection(id)
        insertAll(collections)
    }
}