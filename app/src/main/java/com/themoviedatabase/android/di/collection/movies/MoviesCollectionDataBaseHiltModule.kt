package com.themoviedatabase.android.di.collection.movies

import android.content.Context
import androidx.room.Room
import com.themoviedatabase.android.data.db.MovieDataBase
import com.themoviedatabase.android.data.db.dao.CollectionsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class MoviesCollectionDataBaseHiltModule {
    @Provides
    fun provideChannelDao(appDatabase: MovieDataBase): CollectionsDao {
        return appDatabase.daoCollection()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDataBase {
        return Room.databaseBuilder(
            appContext,
            MovieDataBase::class.java,
            "TMDB"
        ).build()
    }
}