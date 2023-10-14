package com.mayann.domain.model.track

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Parcelize
data class Track(
    @SerializedName("trackId") var id: String,
    @SerializedName("trackName") var name: String? = null,
    @SerializedName("artworkUrl100") var artwork: String? = null,
    @SerializedName("currency") var currency: String? = null,
    @SerializedName("trackPrice") var price: Double? = 0.0,
    @SerializedName("primaryGenreName") var genre: String? = null,
    @SerializedName("shortDescription") var shortDescription: String? = null,
    @SerializedName("longDescription") var longDescription: String? = null
) : Parcelable