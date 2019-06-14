package com.piotrek1543.android.boilerplate.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.piotrek1543.android.boilerplate.remote.mapper.*
import com.piotrek1543.android.boilerplate.remote.model.ForecastModel
import com.piotrek1543.android.boilerplate.remote.test.factory.ForecastFactory
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class ForecastRemoteImplTest {

    private lateinit var entityMapper: ForecastEntityMapper
    private lateinit var forecastService: ForecastService

    private val coordEntityMapper = CoordEntityMapper()
    private val cityEntityMapper = CityEntityMapper(coordEntityMapper)
    private val cloudsEntityMapper = CloudsEntityMapper()
    private val mainEntityMapper = MainEntityMapper()
    private val podEntityMapper = PodEntityMapper()
    private val rainEntityMapper = RainEntityMapper()
    private val snowEntityMapper = SnowEntityMapper()
    private val windEntityMapper = WindEntityMapper()
    private val weatherEntityMapper = WeatherEntityMapper()
    private val listEntityMapper = ListEntityMapper(
            cloudsEntityMapper = cloudsEntityMapper,
            mainEntityMapper = mainEntityMapper,
            podEntityMapper = podEntityMapper,
            rainEntityMapper = rainEntityMapper,
            snowEntityMapper = snowEntityMapper,
            windEntityMapper = windEntityMapper,
            weatherEntityMapper = weatherEntityMapper
    )
    private val forecastEntityMapper = ForecastEntityMapper(cityEntityMapper, listEntityMapper)

    private lateinit var forecastRemoteImpl: ForecastRemoteImpl

    @Before
    fun setup() {
        entityMapper = forecastEntityMapper
        forecastService = mock()
        forecastRemoteImpl = ForecastRemoteImpl(forecastService, entityMapper)
    }

    //<editor-fold desc="Get Forecasts">
    @Test
    fun getForecastsCompletes() {
        stubForecastServiceGetForecasts(Flowable.just(ForecastFactory.makeForecastResponse()))
        val testObserver = forecastRemoteImpl.getForecast().test()
        testObserver.assertComplete()
    }

    @Test
    fun getForecastsReturnsData() {
        val forecastResponse = ForecastFactory.makeForecastResponse()
        stubForecastServiceGetForecasts(Flowable.just(forecastResponse))
        val forecastEntity = entityMapper.mapFromRemote(forecastResponse)
        val testObserver = forecastRemoteImpl.getForecast().test()
        testObserver.assertValue(forecastEntity)
    }
    //</editor-fold>

    private fun stubForecastServiceGetForecasts(observable:
                                                Flowable<ForecastModel>) {
        val query = Constants.QUERY
        val mode = Constants.MODE
        val units = Constants.UNITS
        val type = Constants.TYPE
        val appId = Constants.APPID
        val lang = Locale.getDefault().language
        val service = forecastService[query, mode, units, type, lang, appId]

        whenever(service)
                .thenReturn(observable)
    }
}