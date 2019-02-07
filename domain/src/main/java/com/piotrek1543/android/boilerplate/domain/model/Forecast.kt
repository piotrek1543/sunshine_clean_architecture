package com.piotrek1543.android.boilerplate.domain.model

/**
 * Representation for a [Forecast] fetched from an external layer data source
 */
data class Forecast (
        val city: City? = null,
        val cod: String? = null,
        val message: Double? = null,
        val cnt: Int? = null,
        val list: kotlin.collections.List<List>? = null
)