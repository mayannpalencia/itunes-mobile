package com.mayann.itunesmobile.features.main

import kotlinx.coroutines.flow.MutableSharedFlow

class FakeTrackUseCase {

    private val fakeFlow = MutableSharedFlow<MainState>()

    suspend fun emit(value: MainState) = fakeFlow.emit(value)
}