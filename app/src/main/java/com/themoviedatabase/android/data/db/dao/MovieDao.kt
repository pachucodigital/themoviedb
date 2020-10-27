package com.themoviedatabase.android.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.themoviedatabase.android.data.db.entity.CollectionEntity
import com.themoviedatabase.android.data.db.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieEntity")
    fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM MovieEntity WHERE id IN (:id)")
    fun getMovie(id: IntArray): List<MovieEntity>

    @Insert
    fun insert(users: MovieEntity)

    @Delete
    fun delete(user: MovieEntity)
}