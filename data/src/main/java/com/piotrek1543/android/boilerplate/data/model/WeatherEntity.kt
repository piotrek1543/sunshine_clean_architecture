package com.piotrek1543.android.boilerplate.data.model

/**
 * Representation for a [WeatherEntity] fetched from an external layer data source
 */
data class WeatherEntity(
        val id: Int? = null,
        val main: String? = null,
        val description: String? = null,
        val icon: String? = null,
        val listDt: Long? = null
)