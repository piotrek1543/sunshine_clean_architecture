package com.piotrek1543.android.boilerplate.data.source

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.data.repository.ForecastCache
import com.piotrek1543.android.boilerplate.data.test.factory.ForecastFactory
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ForecastCacheDataStoreTest {

    private lateinit var forecastCacheDataStore: ForecastCacheDataStore

    private lateinit var forecastCache: ForecastCache

    @Before
    fun setUp() {
        forecastCache = mock()
        forecastCacheDataStore = ForecastCacheDataStore(forecastCache)
    }

    //<editor-fold desc="Clear Forecasts">
    @Test
    fun clearForecastsCompletes() {
        stubForecastCacheClearForecasts(Completable.complete())
        val testObserver = forecastCacheDataStore.clearForecast().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Save Forecasts">
    @Test
    fun saveForecastsCompletes() {
        stubForecastCacheSaveForecasts(Completable.complete())
        val testObserver = forecastCacheDataStore.saveForecast(
                ForecastFactory.makeForecastEntity()).test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Get Forecasts">
    @Test
    fun getForecastsCompletes() {
        stubForecastCacheGetForecasts(Flowable.just(ForecastFactory.makeForecastEntity()))
        val testObserver = forecastCacheDataStore.getForecast().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubForecastCacheSaveForecasts(completable: Completable) {
        whenever(forecastCache.saveForecast(any()))
                .thenReturn(completable)
    }

    private fun stubForecastCacheGetForecasts(single: Flowable<ForecastEntity>) {
        whenever(forecastCache.getForecast())
                .thenReturn(single)
    }

    private fun stubForecastCacheClearForecasts(completable: Completable) {
        whenever(forecastCache.clearForecast())
                .thenReturn(completable)
    }
    //</editor-fold>

}