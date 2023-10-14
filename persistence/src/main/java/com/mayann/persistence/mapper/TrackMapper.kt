package com.mayann.persistence.mapper

import com.mayann.persistence.features.track.Track


/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @use Mapping of the Track domain to our local Track object for saving.
 * @desc Mobile Developer
 * @desc Mobile Developer
 * @since 1.0
 */
fun List<com.mayann.domain.model.track.Track>.mapTrackDomainToLocal(): List<Track> {
    return this.map { track ->
        Track(
            id = track.id,
            name = track.name,
            artwork = track.artwork,
            currency = track.currency,
            price = track.price,
            genre = track.genre,
            shortDescription = track.shortDescription,
            longDescription = track.longDescription,
        )
    }
}
