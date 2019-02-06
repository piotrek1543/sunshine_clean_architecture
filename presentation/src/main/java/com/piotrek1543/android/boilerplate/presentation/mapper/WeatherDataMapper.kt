package com.piotrek1543.android.boilerplate.presentation.mapper

import com.piotrek1543.android.boilerplate.domain.model.WeatherData
import com.piotrek1543.android.boilerplate.presentation.model.WeatherDataView
import javax.inject.Inject

/**
 * Map a [WeatherDataView] to and from a [WeatherData] instance when data is moving between
 * this layer and the Domain layer
 */
open class WeatherDataMapper @Inject constructor() : Mapper<WeatherDataView, WeatherData> {

    /**
     * Map a [WeatherData] instance to a [WeatherDataView] instance
     */
    override fun mapToView(type: WeatherData): WeatherDataView {
        return WeatherDataView(cod = type.cod, cnt = type.cnt, message = type.message)
    }


}