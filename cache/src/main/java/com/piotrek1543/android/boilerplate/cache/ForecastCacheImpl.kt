package com.piotrek1543.android.boilerplate.cache

import com.piotrek1543.android.boilerplate.cache.db.SunshineDatabase
import com.piotrek1543.android.boilerplate.cache.mapper.ForecastEntityMapper
import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.data.repository.ForecastCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Forecast instances. This class implements the
 * [ForecastCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class ForecastCacheImpl @Inject constructor(val sunshineDatabase: SunshineDatabase,
                                            private val entityMapper: ForecastEntityMapper,
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
            sunshineDatabase.cachedForecastDao().insertForecast(
                    entityMapper.mapToCached(forecast))
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [ForecastEntity] instances from the database.
     */
    override fun getForecast(): Flowable<ForecastEntity> {
        return Flowable.defer {
            Flowable.just(sunshineDatabase.cachedForecastDao().getForecast())
        }.map { entityMapper.mapFromCached(it) }
    }

    /**
     * Check whether there are instances of [CachedForecast] stored in the cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(sunshineDatabase.cachedForecastDao().getForecast() == null)
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