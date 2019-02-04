package org.buffer.android.boilerplate.domain.model

/**
 * Representation for a [WeatherData] fetched from an external layer data source
 */
data class WeatherData (
        val city: City? = null,
        val cod: String? = null,
        val message: Double? = null,
        val cnt: Int? = null,
        val list: kotlin.collections.List<List>? = null
)