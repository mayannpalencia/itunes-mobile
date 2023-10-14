package com.mayann.itunesmobile.features.main

import com.mayann.data.interactor.TrackUseCase
import com.mayann.persistence.features.session.SessionLocalSource
import com.mayann.persistence.features.track.TrackLocalSource
import io.mockk.mockk
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @use Basic unit testing
 * @desc Mobile Developer
 * @since 1.0
 */
@RunWith(JUnit4::class)
class MainViewModelTest {

    private var trackUseCase: TrackUseCase = mockk()
    private var trackLocalSource: TrackLocalSource = mockk()
    private var sessionLocalSource: SessionLocalSource = mockk()
    private var mockWebServer = MockWebServer()

    @Before
    fun setup() {
        val viewModel = MainViewModel(trackUseCase, trackLocalSource, sessionLocalSource)

        val save = viewModel.saveFavorite("1234")

        fakeTrackUseCase.emit(MainState.ShowLoading)

        ass()
    }
}