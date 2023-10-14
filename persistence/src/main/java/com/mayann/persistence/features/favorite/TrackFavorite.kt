package com.mayann.persistence.features.favorite

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
data class TrackFavorite(
    val id: String,
    val name: String? = null,
    val artwork: String? = null,
    val genre: String? = null,
    val currency: String? = null,
    val price: Double? = 0.0,
    val shortDescription: String? = null,
    val longDescription: String? = null,
    var favoriteCount: Int = 0,
)
