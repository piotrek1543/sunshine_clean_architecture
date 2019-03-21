package com.piotrek1543.android.boilerplate.domain.test.factory

import com.piotrek1543.android.boilerplate.domain.model.City
import com.piotrek1543.android.boilerplate.domain.model.Forecast
import com.piotrek1543.android.boilerplate.domain.test.factory.DataFactory.Factory.randomDouble
import com.piotrek1543.android.boilerplate.domain.test.factory.DataFactory.Factory.randomInt
import com.piotrek1543.android.boilerplate.domain.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Bufferoo related instances
 */
class ForecastFactory {

    companion object Factory {

        fun makeForecast(): Forecast {
            return Forecast(
                    City(id = 1000, name = "Test City"),
                    cod = randomUuid(),
                    message = randomDouble(),
                    cnt = randomInt()
            )
        }

    }

}