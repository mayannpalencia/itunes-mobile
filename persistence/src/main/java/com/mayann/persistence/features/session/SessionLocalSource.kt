package com.mayann.persistence.features.session

import com.mayann.persistence.datastore.ItunesDataStore
import com.mayann.persistence.datastore.Keys
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@OptIn(DelicateCoroutinesApi::class)
class SessionLocalSource @Inject constructor(
    private val dataStore: ItunesDataStore?
) {

    suspend fun saveLastDateVisited(date: String) {
        dataStore?.write(
            Keys.KEY_LAST_DATE_VISITED,
            date
        )
    }

    suspend fun getLastDateVisited(): String
      = dataStore?.getValue(Keys.KEY_LAST_DATE_VISITED).orEmpty()


    suspend fun saveLastScreenVisited(className: String) {
        dataStore?.write(
            Keys.KEY_LAST_SCREEN_VISITED,
            className
        )
    }

    suspend fun getLastScreenVisited(): String
      = dataStore?.getValue(Keys.KEY_LAST_SCREEN_VISITED).orEmpty()

    suspend fun saveLastTrackIdSelected(trackId: String) {
        dataStore?.write(
            Keys.KEY_LAST_TRACK_ID_SELECTED,
            trackId
        )
    }

    suspend fun getLastTrackIdSelected(): String
      = dataStore?.getValue(Keys.KEY_LAST_TRACK_ID_SELECTED).orEmpty()

}