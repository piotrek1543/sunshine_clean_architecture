package org.buffer.android.boilerplate.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.buffer.android.boilerplate.cache.db.SunshineDatabase
import org.buffer.android.boilerplate.cache.mapper.WeatherDataEntityMapper
import org.buffer.android.boilerplate.cache.model.CachedWeatherData
import org.buffer.android.boilerplate.data.model.WeatherDataEntity
import org.buffer.android.boilerplate.data.repository.WeatherDataCache
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving WeatherData instances. This class implements the
 * [WeatherDataCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class WeatherDataCacheImpl @Inject constructor(val sunshineDatabase: SunshineDatabase,
                                               private val entityMapper: WeatherDataEntityMapper,
                                               private val preferencesHelper: PreferencesHelper) :
        WeatherDataCache {

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearWeatherData(): Completable {
        return Completable.defer {
            sunshineDatabase.cachedWeatherDataDao().clearWeatherData()
            Completable.complete()
        }
    }

    /**
     * Save the given list of [WeatherDataEntity] instances to the database.
     */
    override fun saveWeatherData(weatherData: WeatherDataEntity): Completable {
        return Completable.defer {
            sunshineDatabase.cachedWeatherDataDao().insertWeatherData(
                    entityMapper.mapToCached(weatherData))
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [WeatherDataEntity] instances from the database.
     */
    override fun getWeatherData(): Flowable<WeatherDataEntity> {
        return Flowable.defer {
            Flowable.just(sunshineDatabase.cachedWeatherDataDao().getWeatherData())
        }.map { entityMapper.mapFromCached(it) }
    }

    /**
     * Check whether there are instances of [CachedWeatherData] stored in the cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(sunshineDatabase.cachedWeatherDataDao().getWeatherData() == null)
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
    }

}