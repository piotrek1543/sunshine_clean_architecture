package org.buffer.android.boilerplate.domain.model

import org.buffer.android.boilerplate.domain.model.*

/**
 * Representation for a [List] fetched from an external layer data source
 */
data class List(
        val dt: Int? = null,
        val main: Main? = null,
        val weather: kotlin.collections.List<Weather>? = null,
        val clouds: Clouds? = null,
        val wind: Wind? = null,
        val rain: Rain? = null,
        val snow: Snow? = null,
        val pod: Pod? = null,
        val dtTxt: String? = null
)
