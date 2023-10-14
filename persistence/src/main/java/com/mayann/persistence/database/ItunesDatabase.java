package com.mayann.persistence.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mayann.persistence.dao.TrackDao;
import com.mayann.persistence.features.favorite.Favorite;
import com.mayann.persistence.features.track.Track;

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Database(entities = {Track.class, Favorite.class}, version = 1)
public abstract class ItunesDatabase extends RoomDatabase {
    public abstract TrackDao trackDao();

}
