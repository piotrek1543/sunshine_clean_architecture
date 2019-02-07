package com.piotrek1543.android.boilerplate.data.source

import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.data.repository.ForecastCache
import com.piotrek1543.android.boilerplate.data.repository.ForecastDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [ForecastDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class ForecastCacheDataStore @Inject constructor(private val forecastCache: ForecastCache) :
        ForecastDataStore {

    /**
     * Clear Forecast from the cache
     */
    override fun clearForecast(): Completable {
        return forecastCache.clearForecast()
    }

    /**
     * Save a given [List] of [ForecastEntity] instances to the cache
     */
    override fun saveForecast(forecast: ForecastEntity): Completable {
        return forecastCache.saveForecast(forecast)
                .doOnComplete {
                    forecastCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    /**
     * Retrieve a list of [ForecastEntity] instance from the cache
     */
    override fun getForecast(): Flowable<ForecastEntity> {
        return forecastCache.getForecast()
    }

    /**
     * Retrieve a list of [ForecastEntity] instance from the cache
     */
    override fun isCached(): Single<Boolean> {
        return forecastCache.isCached()
    }

}