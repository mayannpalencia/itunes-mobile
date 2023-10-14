package com.mayann.persistence.module

import com.mayann.persistence.datastore.ItunesDataStore
import com.mayann.persistence.features.session.SessionLocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModule {

    @Provides
    fun provideSessionLocalSource(dataStore: ItunesDataStore) = SessionLocalSource(dataStore)
}