package com.piotrek1543.android.boilerplate.data.model


/**
 * Representation for a [CoordEntity] fetched from an external layer data source
 */
data class CoordEntity(
        val cityId: Long? = null,
        val lon: Double? = null,
        val lat: Double? = null
)
