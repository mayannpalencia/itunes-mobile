package com.mayann.itunesmobile.extensions

import android.graphics.Rect
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
fun View.gone() {
    this.visibility = GONE
}

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.invisible() {
    this.visibility = INVISIBLE
}

fun View.setVisible(visible: Boolean, resize: Boolean) {
    this.visibility = if (visible) VISIBLE else if (resize) GONE else INVISIBLE
}

fun View.setEnable(enable: Boolean) {
    this.isEnabled = enable
}

fun View.visibilityChanged(action: (View) -> Unit) {
    this.viewTreeObserver.addOnGlobalLayoutListener {
        val newVis: Int = this.visibility
        if (this.tag as Int? != newVis) {
            this.tag = this.visibility

            // visibility has changed
            action(this)
        }
    }
}

fun View.isKeyboardShown(): Boolean {
    val softKeyboardHeight = 100
    val r = Rect()
    this.getWindowVisibleDisplayFrame(r)
    val dm = this.resources.displayMetrics
    val heightDiff = this.bottom - r.bottom
    return heightDiff > softKeyboardHeight * dm.density
}
