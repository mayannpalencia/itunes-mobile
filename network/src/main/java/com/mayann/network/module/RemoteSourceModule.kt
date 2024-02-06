package com.mayann.network.module

import com.mayann.network.features.track.TrackRemoteSource
import com.mayann.network.features.track.TrackService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Module
@InstallIn(SingletonComponent::class)
class RemoteSourceModule {

    @Provides
    @Singleton
    fun provideTrackRemoteSource(trackService: TrackService) =
        TrackRemoteSource(
            trackService = trackService
        )

}