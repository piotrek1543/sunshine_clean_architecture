package com.piotrek1543.android.boilerplate.data.model

/**
 * Representation for a [ListEntity] fetched from an external layer data source
 */
data class ListEntity(
        val dt: Int? = null,
        val mainEntity: MainEntity? = null,
        val forecastEntity: kotlin.collections.List<ForecastEntity>? = null,
        val cloudsEntity: CloudsEntity? = null,
        val windEntity: WindEntity? = null,
        val rainEntity: RainEntity? = null,
        val snowEntity: SnowEntity? = null,
        val podEntity: PodEntity? = null,
        val dtTxt: String? = null
)
