package com.mayann.persistence.module

import android.content.Context
import androidx.room.Room
import com.mayann.persistence.dao.TrackDao
import com.mayann.persistence.database.ItunesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Module
@InstallIn(ViewModelComponent::class)
class DaoModule{

    @Provides
    @ViewModelScoped
    fun providesTrackDao(@ApplicationContext appContext: Context): TrackDao = Room.databaseBuilder(appContext, ItunesDatabase::class.java, "itunes_database")
            .fallbackToDestructiveMigration().build().trackDao()
}