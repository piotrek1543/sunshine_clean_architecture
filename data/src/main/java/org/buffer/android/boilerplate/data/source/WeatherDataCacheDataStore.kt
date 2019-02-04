package org.buffer.android.boilerplate.data.source

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.WeatherDataEntity
import org.buffer.android.boilerplate.data.repository.WeatherDataCache
import org.buffer.android.boilerplate.data.repository.WeatherDataDataStore
import javax.inject.Inject

/**
 * Implementation of the [WeatherDataDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class WeatherDataCacheDataStore @Inject constructor(private val weatherDataCache: WeatherDataCache) :
        WeatherDataDataStore {

    /**
     * Clear all WeatherDatas from the cache
     */
    override fun clearWeatherData(): Completable {
        return weatherDataCache.clearWeatherData()
    }

    /**
     * Save a given [List] of [WeatherDataEntity] instances to the cache
     */
    override fun saveWeatherData(weatherData: WeatherDataEntity): Completable {
        return weatherDataCache.saveWeatherData(weatherData)
                .doOnComplete {
                    weatherDataCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    /**
     * Retrieve a list of [WeatherDataEntity] instance from the cache
     */
    override fun getWeatherData(): Flowable<WeatherDataEntity> {
        return weatherDataCache.getWeatherData()
    }

    /**
     * Retrieve a list of [WeatherDataEntity] instance from the cache
     */
    override fun isCached(): Single<Boolean> {
        return weatherDataCache.isCached()
    }

}