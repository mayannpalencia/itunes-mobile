package com.mayann.domain.model.base

import android.os.Parcelable
import androidx.annotation.Keep
import com.mayann.domain.model.track.Track
import kotlinx.parcelize.Parcelize

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Keep
@Parcelize
data class Response(
    val resultCount: Int? = 0,
    val results: List<Track>? = emptyList(),
) : Parcelable