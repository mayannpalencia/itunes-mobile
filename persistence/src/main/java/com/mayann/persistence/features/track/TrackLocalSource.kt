package com.mayann.persistence.features.track

import com.mayann.domain.dispatcher.dispatcherIO
import com.mayann.persistence.dao.TrackDao
import com.mayann.persistence.features.favorite.Favorite
import com.mayann.persistence.features.favorite.TrackFavorite
import com.mayann.persistence.mapper.mapTrackDomainToLocal
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
class TrackLocalSource @Inject constructor(
    private val trackDao: TrackDao
) {

    suspend fun getTracks(): List<TrackFavorite> {
        return withContext(dispatcherIO) {
            trackDao.getTracks()
        }
    }

    suspend fun getTrack(trackId: String): TrackFavorite {
        return withContext(dispatcherIO) {
            trackDao.getTrack(trackId = trackId)
        }
    }

    suspend fun saveTracks(tracks: List<com.mayann.domain.model.track.Track>): List<Long> {
        return withContext(dispatcherIO) {
            trackDao.nukeTracks()
            trackDao.insertTracks(tracks.mapTrackDomainToLocal())
        }
    }

    suspend fun saveFavorite(trackId: String, dateAdded: String): Long {
        return withContext(dispatcherIO) {
            trackDao.insertTrackFavorite(
                Favorite(id = trackId, dateAdded = dateAdded)
            )
        }
    }

    suspend fun removeFavorite(trackId: String) {
        return withContext(dispatcherIO) {
            trackDao.deleteFavorite(trackId = trackId)
        }
    }

}