package org.buffer.android.boilerplate.remote.mapper

import org.buffer.android.boilerplate.data.model.WeatherDataEntity
import org.buffer.android.boilerplate.remote.model.WeatherDataModel
import javax.inject.Inject

/**
 * Map a [WeatherDataModel] to and from a [WeatherDataEntity] instance when data is moving between
 * this later and the Data layer
 */
open class WeatherDataEntityMapper @Inject constructor() : EntityMapper<WeatherDataModel, WeatherDataEntity> {

    /**
     * Map an instance of a [WeatherDataModel] to a [WeatherDataEntity] model
     */
    override fun mapFromRemote(type: WeatherDataModel): WeatherDataEntity {
        return WeatherDataEntity(cod = type.cod, cnt = type.cnt, message = type.message)
    }

}