package com.piotrek1543.android.boilerplate.data

import com.piotrek1543.android.boilerplate.data.mapper.ForecastMapper
import com.piotrek1543.android.boilerplate.data.source.ForecastDataStoreFactory
import com.piotrek1543.android.boilerplate.domain.model.Forecast
import com.piotrek1543.android.boilerplate.domain.repository.ForecastRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Provides an implementation of the [ForecastRepository] interface for communicating to and from
 * data sources
 */
class ForecastDataRepository @Inject constructor(private val factory: ForecastDataStoreFactory,
                                                 private val forecastMapper: ForecastMapper) :
        ForecastRepository {
    override fun clearForecast(): Completable {
        return factory.retrieveCacheDataStore().clearForecast()
    }

    override fun saveForecast(forecast: Forecast): Completable {
        return factory.retrieveCacheDataStore().saveForecast(forecastMapper.mapToEntity(forecast))
    }

    override fun getForecast(): Flowable<Forecast> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retrieveDataStore(it).getForecast()
                }
                .flatMap {
                    Flowable.just(forecastMapper.mapFromEntity(it))
                }
                .flatMap {
                    saveForecast(it).toSingle { it }.toFlowable()
                }
    }
}