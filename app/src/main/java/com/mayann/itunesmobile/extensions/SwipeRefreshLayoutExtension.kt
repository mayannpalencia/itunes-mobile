package com.mayann.itunesmobile.extensions

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
fun SwipeRefreshLayout.setCustomColorSchemeColors(color: Int) {
    setColorSchemeColors(color)
}

fun SwipeRefreshLayout.setRefreshState(isRefreshing: Boolean) {
    this.post { this.isRefreshing = isRefreshing }
}
