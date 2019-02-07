package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedForecast
import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import javax.inject.Inject

/**
 * Map a [CachedForecast] instance to and from a [ForecastEntity] instance when data is moving between
 * this later and the Data layer
 */
open class ForecastEntityMapper @Inject constructor() :
        EntityMapper<CachedForecast, ForecastEntity> {

    /**
     * Map a [ForecastEntity] instance to a [CachedForecast] instance
     */
    override fun mapToCached(type: ForecastEntity): CachedForecast {
        return CachedForecast(cod = type.cod, cnt = type.cnt, message = type.message)
    }

    /**
     * Map a [CachedForecast] instance to a [ForecastEntity] instance
     */
    override fun mapFromCached(type: CachedForecast): ForecastEntity {
        return ForecastEntity(cod = type.cod, cnt = type.cnt, message = type.message)
    }

}