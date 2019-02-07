package com.piotrek1543.android.boilerplate.data.repository

import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import io.reactivex.Flowable

/**
 * Interface defining methods for the caching of Forecast. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface ForecastRemote {

    /**
     * Retrieve Forecast, from the cache
     */
    fun getForecast(): Flowable<ForecastEntity>

}