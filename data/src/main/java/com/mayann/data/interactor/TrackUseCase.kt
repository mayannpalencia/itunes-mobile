package com.mayann.data.interactor

import com.mayann.data.repository.TrackRepositoryImpl
import javax.inject.Inject

class TrackUseCase @Inject constructor(
    private val trackRepositoryImpl: TrackRepositoryImpl
) {

    suspend fun searchTracks(query: String) =
        trackRepositoryImpl.searchTracks(query = query)

}