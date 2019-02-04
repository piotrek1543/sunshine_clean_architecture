package org.buffer.android.boilerplate.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import org.buffer.android.boilerplate.domain.model.WeatherData

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface WeatherDataRepository {

    fun clearWeatherData(): Completable

    fun saveWeatherData(WeatherData: WeatherData): Completable

    fun getWeatherData(): Flowable<WeatherData>

}