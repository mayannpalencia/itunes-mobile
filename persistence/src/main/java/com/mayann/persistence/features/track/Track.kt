package com.mayann.persistence.features.track

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Entity(tableName = "tracks")
@Parcelize
data class Track(
    @PrimaryKey val id: String,
    val name: String? = null,
    val artwork: String? = null,
    val genre: String? = null,
    val currency: String? = null,
    val price: Double? = 0.0,
    val shortDescription: String? = null,
    val longDescription: String? = null
) : Parcelable
