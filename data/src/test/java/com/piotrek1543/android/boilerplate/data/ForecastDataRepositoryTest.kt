package com.piotrek1543.android.boilerplate.data

import com.nhaarman.mockito_kotlin.*
import com.piotrek1543.android.boilerplate.data.mapper.*
import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.data.repository.ForecastCache
import com.piotrek1543.android.boilerplate.data.repository.ForecastDataStore
import com.piotrek1543.android.boilerplate.data.repository.ForecastRemote
import com.piotrek1543.android.boilerplate.data.source.ForecastCacheDataStore
import com.piotrek1543.android.boilerplate.data.source.ForecastDataStoreFactory
import com.piotrek1543.android.boilerplate.data.source.ForecastRemoteDataStore
import com.piotrek1543.android.boilerplate.data.test.factory.ForecastFactory
import com.piotrek1543.android.boilerplate.domain.model.Forecast
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class ForecastDataRepositoryTest {


    private lateinit var forecastCache: ForecastCache
    private lateinit var forecastRemote: ForecastRemote

    private lateinit var forecastDataRepository: ForecastDataRepository

    private lateinit var forecastDataStoreFactory: ForecastDataStoreFactory
    private lateinit var forecastMapper: ForecastMapper
    private lateinit var forecastCacheDataStore: ForecastCacheDataStore
    private lateinit var forecastRemoteDataStore: ForecastRemoteDataStore

    @Before
    fun setUp() {
        val cityMapper = CityMapper(coordMapper = CoordMapper())
        val listMapper = ListMapper(
                mainMapper = MainMapper(),
                snowMapper = SnowMapper(),
                rainMapper = RainMapper(),
                podMapper = PodMapper(),
                cloudsMapper = CloudsMapper(),
                windMapper = WindMapper(),
                weatherMapper = WeatherMapper()
        )
        forecastMapper = ForecastMapper(cityMapper = cityMapper, listMapper = listMapper)
        forecastDataStoreFactory = mock()
        forecastCacheDataStore = mock()
        forecastRemoteDataStore = mock()
        forecastDataRepository = ForecastDataRepository(forecastDataStoreFactory, forecastMapper)
        stubForecastDataStoreFactoryRetrieveCacheDataStore()
        stubForecastDataStoreFactoryRetrieveRemoteDataStore()
    }

    //<editor-fold desc="Clear Forecast">
    @Test
    fun clearForecastCompletes() {
        stubForecastCacheClearForecast(Completable.complete())
        val testObserver = forecastDataRepository.clearForecast().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearForecastCallsCacheDataStore() {
        stubForecastCacheClearForecast(Completable.complete())
        forecastDataRepository.clearForecast().test()
        verify(forecastCacheDataStore).clearForecast()
    }

    @Test
    fun clearForecastNeverCallsRemoteDataStore() {
        stubForecastCacheClearForecast(Completable.complete())
        forecastDataRepository.clearForecast().test()
        verify(forecastRemoteDataStore, never()).clearForecast()
    }
    //</editor-fold>

    //<editor-fold desc="Save Forecast">
    @Test
    fun saveForecastCompletes() {
        stubForecastCacheSaveForecast(Completable.complete())
        val testObserver = forecastDataRepository.saveForecast(
                ForecastFactory.makeForecast()).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveForecastCallsCacheDataStore() {
        stubForecastCacheSaveForecast(Completable.complete())
        forecastDataRepository.saveForecast(ForecastFactory.makeForecast()).test()
        verify(forecastCacheDataStore).saveForecast(any())
    }

    @Test
    fun saveForecastNeverCallsRemoteDataStore() {
        stubForecastCacheSaveForecast(Completable.complete())
        forecastDataRepository.saveForecast(ForecastFactory.makeForecast()).test()
        verify(forecastRemoteDataStore, never()).saveForecast(any())
    }
    //</editor-fold>

    //<editor-fold desc="Get Forecast">
    @Test
    fun getForecastCompletes() {
        stubForecastCacheDataStoreIsCached(Single.just(true))
        stubForecastDataStoreFactoryRetrieveDataStore(forecastCacheDataStore)
        stubForecastCacheDataStoreGetForecast(Flowable.just(
                ForecastFactory.makeForecastEntity()))
        stubForecastCacheSaveForecast(Completable.complete())
        val testObserver = forecastDataRepository.getForecast().test()
        testObserver.assertComplete()
    }

    @Test
    fun getForecastReturnsData() {
        stubForecastCacheDataStoreIsCached(Single.just(true))
        stubForecastDataStoreFactoryRetrieveDataStore(forecastCacheDataStore)
        stubForecastCacheSaveForecast(Completable.complete())
        val forecast = ForecastFactory.makeForecast()
        val forecastEntity = ForecastFactory.makeForecastEntity()
        stubForecastMapperMapFromEntity(forecastEntity, forecast)
        stubForecastCacheDataStoreGetForecast(Flowable.just(forecastEntity))

        val testObserver = forecastDataRepository.getForecast().test()
        testObserver.assertResult(forecast)
    }

    @Test
    fun getForecastSavesForecastWhenFromCacheDataStore() {
        stubForecastDataStoreFactoryRetrieveDataStore(forecastCacheDataStore)
        stubForecastCacheSaveForecast(Completable.complete())
        forecastDataRepository.saveForecast(ForecastFactory.makeForecast()).test()
        verify(forecastCacheDataStore).saveForecast(any())
    }

    @Test
    fun getForecastNeverSavesForecastWhenFromRemoteDataStore() {
        stubForecastDataStoreFactoryRetrieveDataStore(forecastRemoteDataStore)
        stubForecastCacheSaveForecast(Completable.complete())
        forecastDataRepository.saveForecast(ForecastFactory.makeForecast()).test()
        verify(forecastRemoteDataStore, never()).saveForecast(any())
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubForecastCacheSaveForecast(completable: Completable) {
        whenever(forecastCacheDataStore.saveForecast(any()))
                .thenReturn(completable)
    }

    private fun stubForecastCacheDataStoreIsCached(single: Single<Boolean>) {
        whenever(forecastCacheDataStore.isCached())
                .thenReturn(single)
    }

    private fun stubForecastCacheDataStoreGetForecast(single: Flowable<ForecastEntity>) {
        whenever(forecastCacheDataStore.getForecast())
                .thenReturn(single)
    }

    private fun stubForecastRemoteDataStoreGetForecast(single: Flowable<ForecastEntity>) {
        whenever(forecastRemoteDataStore.getForecast())
                .thenReturn(single)
    }

    private fun stubForecastCacheClearForecast(completable: Completable) {
        whenever(forecastCacheDataStore.clearForecast())
                .thenReturn(completable)
    }

    private fun stubForecastDataStoreFactoryRetrieveCacheDataStore() {
        whenever(forecastDataStoreFactory.retrieveCacheDataStore())
                .thenReturn(forecastCacheDataStore)
    }

    private fun stubForecastDataStoreFactoryRetrieveRemoteDataStore() {
        whenever(forecastDataStoreFactory.retrieveRemoteDataStore())
                .thenReturn(forecastCacheDataStore)
    }

    private fun stubForecastDataStoreFactoryRetrieveDataStore(dataStore: ForecastDataStore) {
        whenever(forecastDataStoreFactory.retrieveDataStore(any()))
                .thenReturn(dataStore)
    }

    private fun stubForecastMapperMapFromEntity(forecastEntity: ForecastEntity,
                                                forecast: Forecast) {
        val forecastMapper: ForecastMapper = mock()
        Mockito.`when`(forecastMapper.mapFromEntity(forecastEntity)).thenReturn(forecast)
    }
    //</editor-fold>

}