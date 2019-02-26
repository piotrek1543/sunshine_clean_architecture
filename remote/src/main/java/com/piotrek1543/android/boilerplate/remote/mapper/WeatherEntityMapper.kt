package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.WeatherEntity
import com.piotrek1543.android.boilerplate.remote.model.WeatherModel
import javax.inject.Inject

/**
 * Map a [WeatherModel] to and from a [WeatherEntity] instance when data is moving between
 * this later and the Data layer
 */
open class WeatherEntityMapper @Inject constructor() : EntityMapper<WeatherModel, WeatherEntity> {

    /**
     * Map an instance of a [WeatherModel] to a [WeatherEntity] model
     */
    override fun mapFromRemote(type: WeatherModel): WeatherEntity = WeatherEntity(
            id = type.id,
            main = type.main,
            description = type.description,
            icon = type.icon,
            listDt = type.listDt
    )

}