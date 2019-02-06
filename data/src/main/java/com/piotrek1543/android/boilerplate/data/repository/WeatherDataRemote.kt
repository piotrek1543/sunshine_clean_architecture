package com.piotrek1543.android.boilerplate.data.repository

import com.piotrek1543.android.boilerplate.data.model.WeatherDataEntity
import io.reactivex.Flowable

/**
 * Interface defining methods for the caching of WeatherData. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface WeatherDataRemote {

    /**
     * Retrieve WeatherData, from the cache
     */
    fun getWeatherData(): Flowable<WeatherDataEntity>

}