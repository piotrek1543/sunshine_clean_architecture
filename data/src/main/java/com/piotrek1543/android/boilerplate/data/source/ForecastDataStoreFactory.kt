package com.piotrek1543.android.boilerplate.data.source

import com.piotrek1543.android.boilerplate.data.repository.ForecastCache
import com.piotrek1543.android.boilerplate.data.repository.ForecastDataStore
import javax.inject.Inject

/**
 * Create an instance of a ForecastDataStore
 */
open class ForecastDataStoreFactory @Inject constructor(
        private val forecastCache: ForecastCache,
        private val ForecastCacheDataStore: ForecastCacheDataStore,
        private val ForecastRemoteDataStore: ForecastRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean): ForecastDataStore {
        if (isCached && !forecastCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): ForecastDataStore {
        return ForecastCacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): ForecastDataStore {
        return ForecastRemoteDataStore
    }

}