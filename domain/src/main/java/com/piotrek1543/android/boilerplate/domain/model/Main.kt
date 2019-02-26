package com.piotrek1543.android.boilerplate.domain.model

/**
 * Representation for a [Main] fetched from an external layer data source
 */
data class Main(
        val temp: Double? = null,
        val tempMin: Double? = null,
        val tempMax: Double? = null,
        val pressure: String? = null,
        val seaLevel: String? = null,
        val grndLevel: String? = null,
        val humidity: Int? = null,
        val tempKf: String? = null,
        val listDt: Long?
)
