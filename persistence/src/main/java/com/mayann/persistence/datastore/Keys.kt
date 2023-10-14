package com.mayann.persistence.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
object Keys {
    // data store name
    const val NAME = "itunes_datastore"

    // keys
    val KEY_LAST_DATE_VISITED = stringPreferencesKey(name = "last_date_visited")
    val KEY_LAST_SCREEN_VISITED = stringPreferencesKey(name = "last_screen_visited")
    val KEY_LAST_TRACK_ID_SELECTED = stringPreferencesKey(name = "last_track_id_selected")
}