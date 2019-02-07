package com.piotrek1543.android.boilerplate.data.repository

import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Bufferroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface ForecastDataStore {

    fun clearForecast(): Completable

    fun saveForecast(forecast: ForecastEntity): Completable

    fun getForecast(): Flowable<ForecastEntity>

    fun isCached(): Single<Boolean>

}