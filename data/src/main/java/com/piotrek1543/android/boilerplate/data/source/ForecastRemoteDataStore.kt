package com.piotrek1543.android.boilerplate.data.source

import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.data.repository.ForecastDataStore
import com.piotrek1543.android.boilerplate.data.repository.ForecastRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [ForecastDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class ForecastRemoteDataStore @Inject constructor(private val forecastRemote: ForecastRemote) :
        ForecastDataStore {

    override fun clearForecast(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveForecast(forecastEntity: ForecastEntity): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [ForecastEntity] instances from the API
     */
    override fun getForecast(): Flowable<ForecastEntity> {
        return forecastRemote.getForecast()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }

}