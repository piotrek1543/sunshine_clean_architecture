package com.piotrek1543.android.boilerplate.cache

import android.util.Log
import com.piotrek1543.android.boilerplate.cache.db.SunshineDatabase
import com.piotrek1543.android.boilerplate.cache.mapper.*
import com.piotrek1543.android.boilerplate.cache.model.CachedForecast
import com.piotrek1543.android.boilerplate.cache.model.CachedList
import com.piotrek1543.android.boilerplate.cache.model.CachedMain
import com.piotrek1543.android.boilerplate.cache.model.CachedWeather
import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.data.repository.ForecastCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.Function4
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
            //fixme: aad primary keys to simplify this
            with(sunshineDatabase) {
                cachedCityDao().clearCity()
                cachedCloudsDao().clearClouds()
                cachedCoordDao().clearCoord()
                cachedForecastDao().clearForecast()
                cachedListDao().clearList()
                cachedMainDao().clearMain()
                cachedPodDao().clearPod()
                cachedRainDao().clearRain()
                cachedSnowDao().clearSnow()
                cachedWeatherDao().clearWeather()
                cachedWindDao().clearWind()
            }

            Completable.complete()
        }
    }

    /**
     * Save the given list of [ForecastEntity] instances to the database.
     */
    override fun saveForecast(forecast: ForecastEntity): Completable {
        return Completable.defer {
            val cachedForecast = forecastEntityMapper.mapToCached(forecast)

            val cachedCity = forecast.cityEntity?.let { cityEntityMapper.mapToCached(it) }

            val cachedList = forecast.listEntity?.map { listEntityMapper.mapToCached(it) }

            val cachedMain = forecast.listEntity?.map { listEntity ->
                listEntity.mainEntity
            }?.map { entity ->
                if (entity != null) mainEntityMapper.mapToCached(entity) else CachedMain()
            }

            val cachedWeatherList = forecast.listEntity
                    ?.mapNotNull { it -> it.weatherEntity }
                    ?.map { weatherEntityMapper.mapToCached(it) }

            val cachedCloudsList = forecast.listEntity
                    ?.mapNotNull { it -> it.cloudsEntity }
                    ?.map { cloudsEntityMapper.mapToCached(it) }

            val cachedWindList = forecast.listEntity
                    ?.mapNotNull { it -> it.windEntity }
                    ?.map { windEntityMapper.mapToCached(it) }

            val cachedRainList = forecast.listEntity
                    ?.mapNotNull { it -> it.rainEntity }
                    ?.map { rainEntityMapper.mapToCached(it) }

            val cachedPodList = forecast.listEntity
                    ?.mapNotNull { it -> it.podEntity }
                    ?.map { podEntityMapper.mapToCached(it) }

            val cachedSnowList = forecast.listEntity
                    ?.mapNotNull { it -> it.snowEntity }
                    ?.map { snowEntityMapper.mapToCached(it) }

            with(sunshineDatabase) {
                cachedForecastDao().insertForecast(cachedForecast)
                cachedListDao().insertAll(cachedList)
                cachedMainDao().insertMain(cachedMain)
                cachedWeatherDao().insertWeather(cachedWeatherList)
                cachedCloudsDao().insertClouds(cachedCloudsList)
                cachedWindDao().insertWind(cachedWindList)
                cachedRainDao().insertRain(cachedRainList)
                cachedPodDao().insertPod(cachedPodList)
                cachedSnowDao().insertSnow(cachedSnowList)
                cachedCityDao().insertCity(cachedCity)
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
            val weatherFlowable = Flowable.just(sunshineDatabase.cachedWeatherDao().getWeather())

            Flowable.zip(
                    forecastFlowable,
                    listFlowable,
                    mainFlowable,
                    weatherFlowable,
                    Function4<CachedForecast?, List<CachedList>?, List<CachedMain>?, List<CachedWeather>?, ForecastEntity>
                    { forecast, list, main, weather ->
                        Log.d(TAG, list.size.toString())
                        Log.d(TAG, main.size.toString())
                        Log.d(TAG, weather.size.toString())
                        forecastEntityMapper.mapFromCached(forecast).copy(
                                listEntity = list.mapIndexed { index: Int, cachedList: CachedList ->
                                    listEntityMapper.mapFromCached(cachedList)
                                            .copy(
                                                    mainEntity = mainEntityMapper.mapFromCached(main[index]),
                                                    weatherEntity = weatherEntityMapper.mapFromCached(weather[index])
                                            )
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
            Single.just(true)
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
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

    companion object {
        private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
        private const val TAG = "ForecastCacheImpl"
    }

}