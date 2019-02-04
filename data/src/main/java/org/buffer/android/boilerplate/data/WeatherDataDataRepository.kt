package org.buffer.android.boilerplate.data

import io.reactivex.Completable
import io.reactivex.Flowable
import org.buffer.android.boilerplate.data.mapper.WeatherDataMapper
import org.buffer.android.boilerplate.data.source.WeatherDataDataStoreFactory
import org.buffer.android.boilerplate.domain.model.WeatherData
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import org.buffer.android.boilerplate.domain.repository.WeatherDataRepository
import javax.inject.Inject

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
class WeatherDataDataRepository @Inject constructor(private val factory: WeatherDataDataStoreFactory,
                                                    private val weatherDataMapper: WeatherDataMapper) :
        WeatherDataRepository {
    override fun clearWeatherData(): Completable {
        return factory.retrieveCacheDataStore().clearWeatherData()
    }

    override fun saveWeatherData(weatherData: WeatherData): Completable {
        return factory.retrieveCacheDataStore().saveWeatherData(weatherDataMapper.mapToEntity(weatherData))
    }

    override fun getWeatherData(): Flowable<WeatherData> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retrieveDataStore(it).getWeatherData()
                }
                .flatMap {
                    Flowable.just( weatherDataMapper.mapFromEntity(it))
                }
                .flatMap {
                    saveWeatherData(it).toSingle { it }.toFlowable()
                }
    }
}