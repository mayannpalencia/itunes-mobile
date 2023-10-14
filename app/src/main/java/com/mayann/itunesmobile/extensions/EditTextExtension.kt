package com.mayann.itunesmobile.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.Timer
import java.util.TimerTask

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */

fun EditText.textChanged(
    onTextChanged: ((CharSequence) -> Unit)? = null,
    afterTextChanged: ((Editable?) -> Unit)? = null,
    beforeTextChanged: ((CharSequence?) -> Unit)? = null
) {
    this.addTextChangedListener(object : TextWatcher {

        private var timer: Timer? = Timer()

        override fun onTextChanged(input: CharSequence, start: Int, before: Int, count: Int) {
            onTextChanged?.let { it(input) }
        }

        override fun afterTextChanged(s: Editable?) {
            timer?.cancel()
            timer = Timer()
            timer?.schedule(
                object : TimerTask() {
                    override fun run() {
                        afterTextChanged?.invoke(s)
                    }
                },
                500
            )
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChanged?.let { it(s) }
        }
    })

}