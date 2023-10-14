package com.mayann.network.features.track

import com.mayann.domain.dispatcher.dispatcherIO
import com.mayann.domain.model.base.Response
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
class TrackRemoteSource @Inject constructor(
    private val trackService: TrackService
) {
    suspend fun searchTracks(query: String): Response =
        withContext(dispatcherIO) {
            trackService.searchTracks(
                term = query,
                country = "au",
                media = "movie"
            )
        }
}