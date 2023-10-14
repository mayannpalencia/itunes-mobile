package com.mayann.itunesmobile.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayann.data.interactor.TrackUseCase
import com.mayann.itunesmobile.extensions.getCurrentTime
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
class MainViewModel @Inject constructor(
    private val trackUseCase: TrackUseCase,
    private val trackLocalSource: TrackLocalSource,
    private val sessionLocalSource: SessionLocalSource,
) : ViewModel() {

    private val mutableMainState: MutableSharedFlow<MainState> = MutableSharedFlow()

    val mainState = mutableMainState.asSharedFlow()

    fun getLastScreenVisited() {
        viewModelScope.launch {
            mutableMainState.emit(MainState.ShowLastScreenVisited(sessionLocalSource.getLastScreenVisited()))
        }
    }

    fun saveLastScreenVisited(className: String) {
        viewModelScope.launch {
            sessionLocalSource.saveLastScreenVisited(className)
        }
    }

    fun getLastDateVisited() {
        viewModelScope.launch {
            mutableMainState.emit(MainState.ShowLastDateVisited(sessionLocalSource.getLastDateVisited()))
        }
    }

    fun saveLastDateVisited() {
        viewModelScope.launch {
            sessionLocalSource.saveLastDateVisited(getCurrentTime())
        }
    }

    fun searchTracks(query: String) {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                runBlocking {
                    mutableMainState.emit(MainState.ShowLoading(false))
                    mutableMainState.emit(MainState.ShowError(error = error))
                    getTracks()
                }
            }
        ) {
            mutableMainState.emit(MainState.ShowLoading(true))
            trackLocalSource.saveTracks(trackUseCase.searchTracks(query))

            mutableMainState.emit(MainState.ShowLoading(false))
            mutableMainState.emit(MainState.ShowTracks(trackLocalSource.getTracks()))
        }
    }

    fun getTracks() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                runBlocking {
                    mutableMainState.emit(MainState.ShowError(error = error))
                }
            }
        ) {
            val tracks = trackLocalSource.getTracks()
            mutableMainState.emit(MainState.ShowTracks(tracks))
        }
    }

    fun selectTrack(trackId: String) {
        viewModelScope.launch {
            sessionLocalSource.saveLastTrackIdSelected(trackId = trackId)
            mutableMainState.emit(MainState.ShowDetailPage(trackId = trackId))
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