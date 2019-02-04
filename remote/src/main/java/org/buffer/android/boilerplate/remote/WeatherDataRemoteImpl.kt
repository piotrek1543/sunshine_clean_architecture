package org.buffer.android.boilerplate.remote

import io.reactivex.Flowable
import org.buffer.android.boilerplate.data.model.WeatherDataEntity
import org.buffer.android.boilerplate.data.repository.WeatherDataRemote
import org.buffer.android.boilerplate.remote.mapper.WeatherDataEntityMapper
import java.util.*
import javax.inject.Inject

/**
 * Remote implementation for retrieving WeatherData instances. This class implements the
 * [WeatherDataRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class WeatherDataRemoteImpl @Inject constructor(private val service: SunshineService,
                                                private val entityMapper: WeatherDataEntityMapper) :
        WeatherDataRemote {

    /**
     * Retrieve a list of [WeatherDataEntity] instances from the [WeatherDataService].
     */
    override fun getWeatherData(): Flowable<WeatherDataEntity> {
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