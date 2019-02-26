package com.piotrek1543.android.boilerplate.domain.model

/**
 * Representation for a [List] fetched from an external layer data source
 */
data class List(
        val dt: Long? = null,
        val main: Main? = null,
        val weather: Weather? = null,
        val clouds: Clouds? = null,
        val wind: Wind? = null,
        val rain: Rain? = null,
        val snow: Snow? = null,
        val pod: Pod? = null,
        val dtTxt: String? = null
)
