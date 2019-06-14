package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedForecast
import com.piotrek1543.android.boilerplate.cache.test.factory.ForecastFactory
import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ForecastEntityMapperTest {

    private lateinit var forecastEntityMapper: ForecastEntityMapper

    @Before
    fun setUp() {
        forecastEntityMapper = ForecastEntityMapper()
    }

    @Test
    fun mapToCachedMapsData() {
        val forecastEntity = ForecastFactory.makeForecastEntity()
        val cachedForecast = forecastEntityMapper.mapToCached(forecastEntity)

        assertForecastDataEquality(forecastEntity, cachedForecast)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedForecast = ForecastFactory.makeCachedForecast()
        val forecastEntity = forecastEntityMapper.mapFromCached(cachedForecast)

        assertForecastDataEquality(forecastEntity, cachedForecast)
    }

    private fun assertForecastDataEquality(forecastEntity: ForecastEntity,
                                           cachedForecast: CachedForecast) {
        assertEquals(forecastEntity.cnt, cachedForecast.cnt)
        assertEquals(forecastEntity.cod, cachedForecast.cod)
        assertEquals(forecastEntity.message, cachedForecast.message)
    }

}