package com.piotrek1543.android.boilerplate.data.source

import com.piotrek1543.android.boilerplate.data.repository.WeatherDataCache
import com.piotrek1543.android.boilerplate.data.repository.WeatherDataDataStore
import javax.inject.Inject

/**
 * Create an instance of a weatherDataDataStore
 */
open class WeatherDataDataStoreFactory @Inject constructor(
        private val weatherDataCache: WeatherDataCache,
        private val weatherDataCacheDataStore: WeatherDataCacheDataStore,
        private val weatherDataRemoteDataStore: WeatherDataRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean): WeatherDataDataStore {
        if (isCached && !weatherDataCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): WeatherDataDataStore {
        return weatherDataCacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): WeatherDataDataStore {
        return weatherDataRemoteDataStore
    }

}