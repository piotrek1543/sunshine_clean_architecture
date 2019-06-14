package com.piotrek1543.android.boilerplate.remote

import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.data.repository.ForecastRemote
import com.piotrek1543.android.boilerplate.remote.mapper.ForecastEntityMapper
import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject

/**
 * Remote implementation for retrieving Forecast instances. This class implements the
 * [ForecastRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class ForecastRemoteImpl @Inject constructor(private val service: ForecastService,
                                             private val entityMapper: ForecastEntityMapper) :
        ForecastRemote {

    /**
     * Retrieve a list of [ForecastEntity] instances from the [ForecastService].
     */
    override fun getForecast(): Flowable<ForecastEntity> {
        val query = Constants.QUERY
        val mode = Constants.MODE
        val units = Constants.UNITS
        val type = Constants.TYPE
        val appId = Constants.APPID
        val lang = Locale.getDefault().language
        return service[query, mode, units, type, lang, appId]
                .map { entityMapper.mapFromRemote(it) }
    }

}