package org.buffer.android.boilerplate.cache.mapper

import org.buffer.android.boilerplate.cache.model.CachedWeatherData
import org.buffer.android.boilerplate.data.model.WeatherDataEntity
import javax.inject.Inject

/**
 * Map a [CachedWeatherData] instance to and from a [WeatherDataEntity] instance when data is moving between
 * this later and the Data layer
 */
open class WeatherDataEntityMapper @Inject constructor():
        EntityMapper<CachedWeatherData, WeatherDataEntity> {

    /**
     * Map a [WeatherDataEntity] instance to a [CachedWeatherData] instance
     */
    override fun mapToCached(type: WeatherDataEntity): CachedWeatherData {
        return CachedWeatherData(cod = type.cod, cnt = type.cnt, message = type.message)
    }

    /**
     * Map a [CachedWeatherData] instance to a [WeatherDataEntity] instance
     */
    override fun mapFromCached(type: CachedWeatherData): WeatherDataEntity {
        return WeatherDataEntity(cod = type.cod, cnt = type.cnt, message = type.message)
    }

}