package com.piotrek1543.android.boilerplate.data.repository

import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Forecast. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface ForecastCache {

    /**
     * Clear all Forecast from the cache.
     */
    fun clearForecast(): Completable

    /**
     * Save a given list of Forecast to the cache.
     */
    fun saveForecast(forecast: ForecastEntity): Completable

    /**
     * Retrieve a list of Forecast, from the cache.
     */
    fun getForecast(): Flowable<ForecastEntity>

    /**
     * Check whether there is a list of Forecast stored in the cache.
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