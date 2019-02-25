package com.piotrek1543.android.boilerplate.cache

import com.piotrek1543.android.boilerplate.cache.db.SunshineDatabase
import com.piotrek1543.android.boilerplate.cache.mapper.*
import com.piotrek1543.android.boilerplate.cache.model.CachedForecast
import com.piotrek1543.android.boilerplate.cache.model.CachedList
import com.piotrek1543.android.boilerplate.cache.model.CachedMain
import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.data.repository.ForecastCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Forecast instances. This class implements the
 * [ForecastCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class ForecastCacheImpl @Inject constructor(
        private val sunshineDatabase: SunshineDatabase,
        private val cityEntityMapper: CityEntityMapper,
        private val cloudsEntityMapper: CloudsEntityMapper,
        private val coordEntityMapper: CoordEntityMapper,
        private val forecastEntityMapper: ForecastEntityMapper,
        private val listEntityMapper: ListEntityMapper,
        private val mainEntityMapper: MainEntityMapper,
        private val podEntityMapper: PodEntityMapper,
        private val rainEntityMapper: RainEntityMapper,
        private val snowEntityMapper: SnowEntityMapper,
        private val weatherEntityMapper: WeatherEntityMapper,
        private val windEntityMapper: WindEntityMapper,
        private val preferencesHelper: PreferencesHelper) :
        ForecastCache {

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearForecast(): Completable {
        return Completable.defer {
            sunshineDatabase.cachedForecastDao().clearForecast()
            Completable.complete()
        }
    }

    /**
     * Save the given list of [ForecastEntity] instances to the database.
     */
    override fun saveForecast(forecast: ForecastEntity): Completable {
        return Completable.defer {
            val cachedCity = forecast.cityEntity?.let { cityEntityMapper.mapToCached(it) }
            val cachedCoord = forecast.cityEntity?.coordEntity?.let { coordEntityMapper.mapToCached(it) }
            val cachedForecast = forecastEntityMapper.mapToCached(forecast)
            val cachedList = forecast.listEntity?.map { listEntityMapper.mapToCached(it) }
            val cachedMain = forecast.listEntity?.map { it.mainEntity }?.map { entity ->
                entity?.let { mainEntityMapper.mapToCached(it) }
            }
            // val cachedWeather = forecast.listEntity?.map { it.weatherEntity }?.get(0)?.let { weatherEntityMapper.mapToCached(it) }


            with(sunshineDatabase) {
                cachedForecastDao().insertForecast(cachedForecast)
                cachedListDao().insertAll(cachedList)
                cachedMainDao().insertMain(cachedMain)
                // cachedWeatherDao().insertWeather(cachedWeather)
               // cachedCityDao().insertCity(cachedCity)
              //  cachedCoordDao().insertCoord(cachedCoord)
            }

            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [ForecastEntity] instances from the database.
     */
    override fun getForecast(): Flowable<ForecastEntity> {
        return Flowable.defer {
            val forecastFlowable = Flowable.just(sunshineDatabase.cachedForecastDao().getForecast())
            val listFlowable = Flowable.just(sunshineDatabase.cachedListDao().getList())
            val mainFlowable = Flowable.just(sunshineDatabase.cachedMainDao().getMain())

            Flowable.zip(
                    forecastFlowable,
                    listFlowable,
                    mainFlowable,
                    Function3<CachedForecast?, List<CachedList>?, List<CachedMain?>?, ForecastEntity>
                    { forecast, list, main ->
                        forecastEntityMapper.mapFromCached(forecast).copy(
                                listEntity = list.mapIndexed { index: Int, cachedList: CachedList ->
                                    listEntityMapper.mapFromCached(cachedList)
                                            .copy(mainEntity = main[index]?.let { mainEntityMapper.mapFromCached(it) })
                                }
                        )
                    })
        }
    }

    /**
     * Check whether there are instances of [CachedForecast] stored in the cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(sunshineDatabase.cachedForecastDao().getForecast() != null)
        }
    }

    /**
     * Set a point in time at when the cache was last updated.
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return false
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

    companion object {
        private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
    }

}