package org.buffer.android.boilerplate.data.model

/**
 * Representation for a [MainEntity] fetched from an external layer data source
 */
data class MainEntity(
    val temp: Double? = null,
    val tempMin: Double? = null,
    val tempMax: Double? = null,
    val pressure: String? = null,
    val seaLevel: String? = null,
    val grndLevel: String? = null,
    val humidity: Int? = null,
    val tempKf: String? = null
)
