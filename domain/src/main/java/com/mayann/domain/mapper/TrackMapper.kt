package com.mayann.domain.mapper

import com.mayann.domain.model.base.Response
import com.mayann.domain.model.track.Track

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @use Mapping of the track response from API to our domain model.
 * This mapper is necessary to filter out the only needed attributes
 * based on the requirement
 * @desc Mobile Developer
 * @since 1.0
 */
fun Response.mapTrackResponseToDomain(): List<Track> {
    return this.results?.map { track ->
        Track(
            id = track.id,
            name = track.name,
            artwork = track.artwork,
            genre = track.genre,
            currency = track.currency,
            price = track.price,
            shortDescription = track.shortDescription,
            longDescription = track.longDescription,
        )
    } ?: emptyList()
}
