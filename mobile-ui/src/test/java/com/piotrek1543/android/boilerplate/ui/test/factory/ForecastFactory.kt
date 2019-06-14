package com.piotrek1543.android.boilerplate.ui.test.factory

import com.piotrek1543.android.boilerplate.presentation.model.ForecastView

/**
 * Factory class for Forecast related instances
 */
class ForecastFactory {

    companion object {
        val forecast = DataFactory.makeForecast()

        fun makeForecastView(): ForecastView {
            val list = forecast.list?.first()
            val main = list?.main
            val weather = list?.weather

            return ForecastView(
                    icon = weather?.id ?: 0,
                    date = list?.dt ?: 0,
                    dateTxt = list?.dtTxt ?: "",
                    description = weather?.description ?: "",
                    tempMax = main?.tempMax ?: 0.0,
                    tempMin = main?.tempMax ?: 0.0
            )
        }

    }

}