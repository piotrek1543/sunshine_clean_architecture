package com.piotrek1543.android.boilerplate.domain.test.factory

import com.piotrek1543.android.boilerplate.domain.model.Forecast

/**
 * Factory class for Forecast related instances
 */
class ForecastFactory {

    companion object Factory {

        fun makeForecast(): Forecast = DataFactory.makeForecast()

    }

}