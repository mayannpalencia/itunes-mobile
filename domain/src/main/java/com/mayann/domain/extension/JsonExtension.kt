package com.mayann.domain.extension

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.serialization.ExperimentalSerializationApi

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@ExperimentalSerializationApi
fun json(): Gson = GsonBuilder().apply {
    setLenient()
}.create()
