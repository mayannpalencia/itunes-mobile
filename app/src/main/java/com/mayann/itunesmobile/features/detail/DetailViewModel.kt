package com.mayann.itunesmobile.features.detail

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayann.itunesmobile.extensions.getCurrentTime
import com.mayann.itunesmobile.features.detail.DetailActivity.Companion.BUNDLE_KEY_TRACK_ID
import com.mayann.persistence.features.session.SessionLocalSource
import com.mayann.persistence.features.track.TrackLocalSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val trackLocalSource: TrackLocalSource,
    private val sessionLocalSource: SessionLocalSource,
) : ViewModel() {

    private val mutableDetailState: MutableSharedFlow<DetailState> = MutableSharedFlow()

    val detailState = mutableDetailState.asSharedFlow()

    fun getIntentExtras(intent: Intent) {

        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                runBlocking {
                    println("Error: $error")
                    mutableDetailState.emit(DetailState.ShowError)
                }
            }
        ) {

            val trackId: String? = intent.extras?.getString(BUNDLE_KEY_TRACK_ID)

            if (trackId.isNullOrEmpty()) {
                getLastTrackIdSelected()
            } else {
                mutableDetailState.emit(DetailState.SetTrackId(trackId = trackId))
            }
        }
    }

    fun saveLastScreenVisited(className: String) {
        viewModelScope.launch {
            sessionLocalSource.saveLastScreenVisited(className)
            if (className.isEmpty()) {
                mutableDetailState.emit(DetailState.ExitPage)
            }
        }
    }

    private fun getLastTrackIdSelected() {
        viewModelScope.launch {
            mutableDetailState.emit(DetailState.SetTrackId(sessionLocalSource.getLastTrackIdSelected()))
        }
    }

    fun getTrackDetails(trackId: String?) {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                runBlocking {
                    println("Error: $error")
                    mutableDetailState.emit(DetailState.ShowError)
                }
            }
        ) {
            if (!trackId.isNullOrEmpty()) {
                mutableDetailState.emit(
                    DetailState.ShowTrackDetail(
                        trackLocalSource.getTrack(
                            trackId = trackId
                        )
                    )
                )
            } else {
                mutableDetailState.emit(DetailState.ShowError)
            }
        }
    }

    fun saveFavorite(trackId: String) {
        viewModelScope.launch {
            trackLocalSource.saveFavorite(
                trackId = trackId,
                dateAdded = getCurrentTime()
            )
        }
    }

    fun removeFavorite(trackId: String) {
        viewModelScope.launch {
            trackLocalSource.removeFavorite(
                trackId = trackId
            )
        }
    }
}