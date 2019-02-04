package org.buffer.android.boilerplate.data.source

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.WeatherDataEntity
import org.buffer.android.boilerplate.data.repository.WeatherDataDataStore
import org.buffer.android.boilerplate.data.repository.WeatherDataRemote
import javax.inject.Inject

/**
 * Implementation of the [WeatherDataDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class WeatherDataRemoteDataStore @Inject constructor(private val weatherDataRemote: WeatherDataRemote) :
        WeatherDataDataStore {

    override fun clearWeatherData(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveWeatherData(weatherDataEntity: WeatherDataEntity): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [WeatherDataEntity] instances from the API
     */
    override fun getWeatherData(): Flowable<WeatherDataEntity> {
        return weatherDataRemote.getWeatherData()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }

}