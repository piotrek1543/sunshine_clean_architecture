package com.piotrek1543.android.boilerplate.data.model

/**
 * Representation for a [WeatherDataEntity] fetched from an external layer data source
 */
data class WeatherDataEntity (
        val cityEntity: CityEntity? = null,
        val cod: String? = null,
        val message: Double? = null,
        val cnt: Int? = null,
        val listEntity: kotlin.collections.List<ListEntity>? = null
)