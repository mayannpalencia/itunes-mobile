package com.mayann.persistence.module;

import android.content.Context;

import androidx.room.Room;

import com.mayann.persistence.dao.TrackDao;
import com.mayann.persistence.database.ItunesDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Module
@DisableInstallInCheck
public class DatabaseModule {
    ItunesDatabase database;
    public DatabaseModule(Context appContext){
        database = Room.databaseBuilder(appContext, ItunesDatabase.class, "itunes_database")
                .fallbackToDestructiveMigration().build();
    }

    @Provides
    @Singleton
    TrackDao providesTrackDao(){
        return database.trackDao();
    }

}
