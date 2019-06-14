package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.data.test.factory.ForecastFactory
import com.piotrek1543.android.boilerplate.domain.model.Forecast
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ForecastMapperTest {

    private lateinit var forecastMapper: ForecastMapper

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
    }

    @Test
    fun mapFromEntityMapsData() {
        val forecastEntity = ForecastFactory.makeForecastEntity()
        val forecast = forecastMapper.mapFromEntity(forecastEntity)

        assertForecastDataEquality(forecastEntity, forecast)
    }

    @Test
    fun mapToEntityMapsData() {
        val cachedForecast = ForecastFactory.makeForecast()
        val forecastEntity = forecastMapper.mapToEntity(cachedForecast)

        assertForecastDataEquality(forecastEntity, cachedForecast)
    }

    private fun assertForecastDataEquality(forecastEntity: ForecastEntity,
                                           forecast: Forecast) {
        assertEquals(forecastEntity.cityEntity?.country, forecast.city?.country)
        assertEquals(forecastEntity.cnt, forecast.cnt)
        assertEquals(forecastEntity.listEntity?.first()?.dt, forecast.list?.first()?.dt)
    }

}

