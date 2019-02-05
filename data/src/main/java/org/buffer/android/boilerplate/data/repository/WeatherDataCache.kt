package org.buffer.android.boilerplate.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.WeatherDataEntity

/**
 * Interface defining methods for the caching of WeatherData. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface WeatherDataCache {

    /**
     * Clear all WeatherData from the cache.
     */
    fun clearWeatherData(): Completable

    /**
     * Save a given list of WeatherData to the cache.
     */
    fun saveWeatherData(weatherData: WeatherDataEntity): Completable

    /**
     * Retrieve a list of WeatherData, from the cache.
     */
    fun getWeatherData(): Flowable<WeatherDataEntity>

    /**
     * Check whether there is a list of WeatherData stored in the cache.
     *
     * @return true if the list is cached, otherwise false
     */
    fun isCached(): Single<Boolean>

    /**
     * Set a point in time at when the cache was last updated.
     *
     * @param lastCache the point in time at when the cache was last updated
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Check if the cache is expired.
     *
     * @return true if the cache is expired, otherwise false
     */
    fun isExpired(): Boolean

}