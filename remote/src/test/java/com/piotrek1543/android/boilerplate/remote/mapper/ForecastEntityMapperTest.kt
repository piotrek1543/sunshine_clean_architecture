package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.remote.test.factory.ForecastFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ForecastEntityMapperTest {

    private lateinit var forecastEntityMapper: ForecastEntityMapper
    private lateinit var cityEntityMapper: CityEntityMapper
    private lateinit var listEntityMapper: ListEntityMapper

    @Before
    fun setUp() {
        cityEntityMapper = CityEntityMapper(CoordEntityMapper())
        listEntityMapper = ListEntityMapper(
                mainEntityMapper = MainEntityMapper(),
                weatherEntityMapper = WeatherEntityMapper(),
                cloudsEntityMapper = CloudsEntityMapper(),
                rainEntityMapper = RainEntityMapper(),
                snowEntityMapper = SnowEntityMapper(),
                windEntityMapper = WindEntityMapper(),
                podEntityMapper = PodEntityMapper()
        )
        forecastEntityMapper = ForecastEntityMapper(cityEntityMapper, listEntityMapper)
    }

    @Test
    fun mapFromRemoteMapsData() {
        val forecastModel = ForecastFactory.makeForecastModel()
        val forecastEntity = forecastEntityMapper.mapFromRemote(forecastModel)

        assertEquals(forecastModel.cnt, forecastEntity.cnt)
        assertEquals(forecastModel.city?.name, forecastEntity.cityEntity?.name)
        assertEquals(forecastModel.city?.coord?.lat, forecastEntity.cityEntity?.coordEntity?.lat)
        assertEquals(forecastModel.city?.coord?.lon, forecastEntity.cityEntity?.coordEntity?.lon)
    }

}