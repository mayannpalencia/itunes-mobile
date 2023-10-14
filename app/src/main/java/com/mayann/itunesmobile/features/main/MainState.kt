package com.mayann.itunesmobile.features.main

import com.mayann.persistence.features.favorite.TrackFavorite

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
sealed class MainState {
    data class ShowLoading(val show: Boolean) : MainState()
    data class ShowLastDateVisited(val date: String) : MainState()
    data class ShowLastScreenVisited(val className: String) : MainState()
    data class ShowTracks(val tracks: List<TrackFavorite>) : MainState()
    data class ShowDetailPage(val trackId: String) : MainState()
    data class ShowError(val error: Any) : MainState()
}