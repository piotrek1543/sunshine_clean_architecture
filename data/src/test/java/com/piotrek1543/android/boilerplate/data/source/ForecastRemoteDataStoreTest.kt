package com.piotrek1543.android.boilerplate.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.data.repository.ForecastRemote
import com.piotrek1543.android.boilerplate.data.test.factory.ForecastFactory
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ForecastRemoteDataStoreTest {

    private lateinit var forecastRemoteDataStore: ForecastRemoteDataStore

    private lateinit var forecastRemote: ForecastRemote

    @Before
    fun setUp() {
        forecastRemote = mock()
        forecastRemoteDataStore = ForecastRemoteDataStore(forecastRemote)
    }

    //<editor-fold desc="Clear Forecasts">
    @Test(expected = UnsupportedOperationException::class)
    fun clearForecastsThrowsException() {
        forecastRemoteDataStore.clearForecast().test()
    }
    //</editor-fold>

    //<editor-fold desc="Save Forecasts">
    @Test(expected = UnsupportedOperationException::class)
    fun saveForecastsThrowsException() {
        forecastRemoteDataStore.saveForecast(ForecastFactory.makeForecastEntity()).test()
    }
    //</editor-fold>

    //<editor-fold desc="Get Forecasts">
    @Test
    fun getForecastsCompletes() {
        stubForecastCacheGetForecasts(Flowable.just(ForecastFactory.makeForecastEntity()))
        val testObserver = forecastRemote.getForecast().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubForecastCacheGetForecasts(single: Flowable<ForecastEntity>) {
        whenever(forecastRemote.getForecast())
                .thenReturn(single)
    }
    //</editor-fold>

}