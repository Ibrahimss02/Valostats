package com.drunken.valostats.models

import com.squareup.moshi.Json

data class Response<T>(
    val status: Int,
    val data: T
)
