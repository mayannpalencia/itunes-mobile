package com.mayann.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.mayann.persistence.features.favorite.Favorite
import com.mayann.persistence.features.favorite.TrackFavorite
import com.mayann.persistence.features.track.Track

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Dao
interface TrackDao {
    @RawQuery
    suspend fun getTracks(query: SupportSQLiteQuery): List<TrackFavorite>

    @RawQuery
    suspend fun getTrack(query: SupportSQLiteQuery): TrackFavorite

    // Get all column of tracks with a customized column `track_favorite` to determine if these tracks are marked as favorite
    suspend fun getTracks(): List<TrackFavorite> {
        val query = "SELECT t.*, (select count(*) from tracks_favorite f where f.id = t.id) as favoriteCount from tracks t"
        val simpleSQLiteQuery = SimpleSQLiteQuery(query)
        return getTracks(simpleSQLiteQuery)
    }

    // Get the track object with a customized column `track_favorite` to determine if this track is marked as favorite
    suspend fun getTrack(trackId: String): TrackFavorite {
        val query = "SELECT t.*, (select count(*) from tracks_favorite f where f.id = t.id) as favoriteCount from tracks t where t.id = ? LIMIT 1"
        val simpleSQLiteQuery = SimpleSQLiteQuery(query, arrayOf(trackId))
        return getTrack(simpleSQLiteQuery)
    }


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(tracks: List<Track>): List<Long>

    @Query("DELETE FROM tracks")
    fun nukeTracks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackFavorite(favorite: Favorite): Long

    @Query("DELETE FROM tracks_favorite where id = :trackId")
    fun deleteFavorite(trackId: String)
}
