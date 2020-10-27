package com.themoviedatabase.android.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CollectionEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "collection_id") val collection_id: String,
    @ColumnInfo(name = "data") val data: String
)