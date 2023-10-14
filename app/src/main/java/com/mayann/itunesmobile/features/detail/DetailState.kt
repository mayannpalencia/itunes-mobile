package com.mayann.itunesmobile.features.detail

import com.mayann.persistence.features.favorite.TrackFavorite

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
sealed class DetailState {
    data class SetTrackId(val trackId: String) : DetailState()
    data class ShowTrackDetail(val track: TrackFavorite) : DetailState()
    data object ExitPage : DetailState()
    data object ShowError : DetailState()
}