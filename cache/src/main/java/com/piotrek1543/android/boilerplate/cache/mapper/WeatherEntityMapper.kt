package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedWeather
import com.piotrek1543.android.boilerplate.data.model.WeatherEntity
import javax.inject.Inject

/**
 * Map a [CachedWeather] instance to and from a [WeatherEntity] instance when data is moving between
 * this later and the Data layer
 */
open class WeatherEntityMapper @Inject constructor() :
        EntityMapper<CachedWeather, WeatherEntity> {

    /**
     * Map a [WeatherEntity] instance to a [CachedWeather] instance
     */
    override fun mapToCached(type: WeatherEntity): CachedWeather =
            CachedWeather(
                    id = type.id,
                    main = type.main,
                    description = type.description,
                    icon = type.icon
            )

    /**
     * Map a [CachedWeather] instance to a [WeatherEntity] instance
     */
    override fun mapFromCached(type: CachedWeather): WeatherEntity =
            WeatherEntity(
                    id = type.id,
                    main = type.main,
                    description = type.description,
                    icon = type.icon
            )

}