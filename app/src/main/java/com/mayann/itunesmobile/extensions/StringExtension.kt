package com.mayann.itunesmobile.extensions


/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
fun String.uppercase(word: String): String {
    return this.replace(word, word.uppercase())
}