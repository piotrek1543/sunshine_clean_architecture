package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.WeatherEntity
import com.piotrek1543.android.boilerplate.domain.model.Weather
import javax.inject.Inject


/**
 * Map a [WeatherEntity] to and from a [Weather] instance when data is moving between
 * this later and the DoWeather layer
 */
open class WeatherMapper @Inject constructor() : Mapper<WeatherEntity, Weather> {

    /**
     * Map a [WeatherEntity] instance to a [Weather] instance
     */
    override fun mapFromEntity(type: WeatherEntity): Weather = Weather(
            id = type.id,
            main = type.main,
            description = type.description,
            icon = type.icon
    )

    /**
     * Map a [Weather] instance to a [WeatherEntity] instance
     */
    override fun mapToEntity(type: Weather): WeatherEntity = WeatherEntity(
            id = type.id,
            main = type.main,
            description = type.description,
            icon = type.icon
    )


}