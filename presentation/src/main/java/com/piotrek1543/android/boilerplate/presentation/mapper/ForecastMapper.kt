package com.piotrek1543.android.boilerplate.presentation.mapper

import com.piotrek1543.android.boilerplate.domain.model.Forecast
import com.piotrek1543.android.boilerplate.domain.model.List
import com.piotrek1543.android.boilerplate.presentation.model.ForecastView
import javax.inject.Inject

/**
 * Map a [ForecastView] to and from a [Forecast] instance when data is moving between
 * this layer and the Domain layer
 */
open class ForecastMapper @Inject constructor() : Mapper<ForecastView, Forecast> {

    /**
     * Map a [Forecast] instance to a [ForecastView] instance
     */
    override fun mapToView(type: List): ForecastView =
            ForecastView(
                    dateTxt = type.dtTxt ?: "",
                    date = type.dt ?: 0,
                    tempMin = type.main?.tempMin ?: 0.0,
                    tempMax = type.main?.tempMax ?: 0.0,
                    description = type.weather?.get(0)?.description ?: "",
                    icon = type.weather?.get(0)?.id ?: 500
            )

}