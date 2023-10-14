package com.mayann.itunesmobile.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
fun View.setVisible(visible: Boolean, resize: Boolean) {
    this.visibility = if (visible) VISIBLE else if (resize) GONE else INVISIBLE
}