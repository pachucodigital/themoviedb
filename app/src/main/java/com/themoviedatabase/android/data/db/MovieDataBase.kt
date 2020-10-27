package com.themoviedatabase.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.themoviedatabase.android.data.db.dao.CollectionsDao
import com.themoviedatabase.android.data.db.dao.MovieDao
import com.themoviedatabase.android.data.db.entity.CollectionEntity
import com.themoviedatabase.android.data.db.entity.MovieEntity


@Database(entities = arrayOf(CollectionEntity::class, MovieEntity::class), version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun daoCollection(): CollectionsDao
    abstract fun dao(): MovieDao

}