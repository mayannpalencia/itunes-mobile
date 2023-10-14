package com.mayann.data.repository

import com.mayann.domain.mapper.mapTrackResponseToDomain
import com.mayann.network.features.track.TrackRemoteSource
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val trackRemoteSource: TrackRemoteSource
) {
    suspend fun searchTracks(query: String) =
        trackRemoteSource.searchTracks(
            query = query
        ).mapTrackResponseToDomain()
}