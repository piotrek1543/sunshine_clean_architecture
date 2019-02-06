package com.piotrek1543.android.boilerplate.data

import com.piotrek1543.android.boilerplate.data.mapper.WeatherDataMapper
import com.piotrek1543.android.boilerplate.data.source.WeatherDataDataStoreFactory
import com.piotrek1543.android.boilerplate.domain.model.WeatherData
import com.piotrek1543.android.boilerplate.domain.repository.WeatherDataRepository
import io.reactivex.Completable
import io.reactivex.Flowable
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
                    Flowable.just(weatherDataMapper.mapFromEntity(it))
                }
                .flatMap {
                    saveWeatherData(it).toSingle { it }.toFlowable()
                }
    }
}