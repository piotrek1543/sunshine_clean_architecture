package com.piotrek1543.android.boilerplate.data.model

/**
 * Representation for a [ListEntity] fetched from an external layer data source
 */
data class ListEntity(
        val dt: Long? = null,
        val mainEntity: MainEntity? = null,
        val weatherEntity: kotlin.collections.List<WeatherEntity>? = null,
        val cloudsEntity: Any? = null,
        val windEntity: WindEntity? = null,
        val rainEntity: RainEntity? = null,
        val snowEntity: SnowEntity? = null,
        val podEntity: PodEntity? = null,
        val dtTxt: String? = null
)
