package com.mayann.persistence.features.favorite

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mayann.persistence.features.track.Track
import kotlinx.parcelize.Parcelize

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Entity(tableName = "tracks_favorite")
@Parcelize
data class Favorite(
    @PrimaryKey val id: String,
    val dateAdded: String? = null
) : Parcelable
