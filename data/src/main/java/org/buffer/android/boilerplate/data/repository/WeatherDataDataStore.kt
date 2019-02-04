package org.buffer.android.boilerplate.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.WeatherDataEntity

/**
 * Interface defining methods for the data operations related to Bufferroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface WeatherDataDataStore {

    fun clearWeatherData(): Completable

    fun saveWeatherData(weatherData: WeatherDataEntity): Completable

    fun getWeatherData(): Flowable<WeatherDataEntity>

    fun isCached(): Single<Boolean>

}