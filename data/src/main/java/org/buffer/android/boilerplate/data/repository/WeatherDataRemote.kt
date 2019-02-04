package org.buffer.android.boilerplate.data.repository

import io.reactivex.Flowable
import org.buffer.android.boilerplate.data.model.WeatherDataEntity

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