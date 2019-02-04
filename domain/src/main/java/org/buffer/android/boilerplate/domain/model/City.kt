package org.buffer.android.boilerplate.domain.model

/**
 * Representation for a [City] fetched from an external layer data source
 */
data class City(
        val id: Int,
        val name: String,
        val coord: Coord? = null,
        val country: String? = null,
        val population: Int? = null,
        val sys: Sys? = null
)
