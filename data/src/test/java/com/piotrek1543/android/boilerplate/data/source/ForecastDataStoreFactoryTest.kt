package com.piotrek1543.android.boilerplate.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.piotrek1543.android.boilerplate.data.repository.ForecastCache
import com.piotrek1543.android.boilerplate.data.repository.ForecastRemote
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ForecastDataStoreFactoryTest {

    private lateinit var forecastDataStoreFactory: ForecastDataStoreFactory

    private lateinit var forecastCache: ForecastCache
    private lateinit var forecastRemote: ForecastRemote
    private lateinit var forecastCacheDataStore: ForecastCacheDataStore
    private lateinit var forecastRemoteDataStore: ForecastRemoteDataStore

    @Before
    fun setUp() {
        forecastCache = mock()
        forecastRemote = mock()
        forecastCacheDataStore = ForecastCacheDataStore(forecastCache)
        forecastRemoteDataStore = ForecastRemoteDataStore(forecastRemote)
        forecastDataStoreFactory = ForecastDataStoreFactory(forecastCache,
                forecastCacheDataStore, forecastRemoteDataStore)
    }

    //<editor-fold desc="Retrieve Data Store">
    @Test
    fun retrieveDataStoreWhenNotCachedReturnsRemoteDataStore() {
        stubForecastCacheIsCached(Single.just(false))
        val forecastDataStore = forecastDataStoreFactory.retrieveDataStore(false)
        assert(forecastDataStore is ForecastRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreWhenCacheExpiredReturnsRemoteDataStore() {
        stubForecastCacheIsCached(Single.just(true))
        stubForecastCacheIsExpired(true)
        val forecastDataStore = forecastDataStoreFactory.retrieveDataStore(true)
        assert(forecastDataStore is ForecastRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreReturnsCacheDataStore() {
        stubForecastCacheIsCached(Single.just(true))
        stubForecastCacheIsExpired(false)
        val forecastDataStore = forecastDataStoreFactory.retrieveDataStore(true)
        assert(forecastDataStore is ForecastCacheDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Retrieve Remote Data Store">
    @Test
    fun retrieveRemoteDataStoreReturnsRemoteDataStore() {
        val forecastDataStore = forecastDataStoreFactory.retrieveRemoteDataStore()
        assert(forecastDataStore is ForecastRemoteDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Retrieve Cache Data Store">
    @Test
    fun retrieveCacheDataStoreReturnsCacheDataStore() {
        val forecastDataStore = forecastDataStoreFactory.retrieveCacheDataStore()
        assert(forecastDataStore is ForecastCacheDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubForecastCacheIsCached(single: Single<Boolean>) {
        whenever(forecastCache.isCached())
                .thenReturn(single)
    }

    private fun stubForecastCacheIsExpired(isExpired: Boolean) {
        whenever(forecastCache.isExpired())
                .thenReturn(isExpired)
    }
    //</editor-fold>

}