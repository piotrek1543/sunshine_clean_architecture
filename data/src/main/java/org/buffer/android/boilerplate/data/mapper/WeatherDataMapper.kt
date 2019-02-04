package org.buffer.android.boilerplate.data.mapper

import org.buffer.android.boilerplate.data.model.WeatherDataEntity
import org.buffer.android.boilerplate.domain.model.WeatherData
import javax.inject.Inject


/**
 * Map a [WeatherDataEntity] to and from a [WeatherData] instance when data is moving between
 * this later and the Domain layer
 */
open class WeatherDataMapper @Inject constructor(): Mapper<WeatherDataEntity, WeatherData> {

    /**
     * Map a [WeatherDataEntity] instance to a [WeatherData] instance
     */
    override fun mapFromEntity(type: WeatherDataEntity): WeatherData {
        return WeatherData(cod = type.cod, cnt = type.cnt, message = type.message)
    }

    /**
     * Map a [WeatherData] instance to a [WeatherDataEntity] instance
     */
    override fun mapToEntity(type: WeatherData): WeatherDataEntity {
        return WeatherDataEntity(cod = type.cod, cnt = type.cnt, message = type.message)
    }


}