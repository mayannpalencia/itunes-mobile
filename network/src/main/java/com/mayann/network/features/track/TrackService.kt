package com.mayann.network.features.track

import com.mayann.domain.model.base.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
interface TrackService {

    @GET("/search")
    suspend fun searchTracks(
        @Query("term") term: String?,
        @Query("country") country: String?,
        @Query("media") media: String?,
        @Query(";all") all: String? = ""
    ): Response
}