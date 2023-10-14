package com.mayann.data.module

import com.mayann.data.repository.TrackRepositoryImpl
import com.mayann.network.features.track.TrackRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTrackRepositoryImpl(
        trackRemoteSource: TrackRemoteSource
    ) = TrackRepositoryImpl(
        trackRemoteSource = trackRemoteSource
    )

}