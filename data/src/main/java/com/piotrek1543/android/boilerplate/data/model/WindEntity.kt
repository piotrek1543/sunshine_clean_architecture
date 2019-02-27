package com.piotrek1543.android.boilerplate.data.model

/**
 * Representation for a [WindEntity] fetched from an external layer data source
 */
data class WindEntity(
        val speed: Double? = null,
        val deg: Float? = null,
        val listDt: Long?
)