package com.piotrek1543.android.boilerplate.ui

import com.piotrek1543.android.boilerplate.ui.mapper.ForecastMapper
import com.piotrek1543.android.boilerplate.ui.test.factory.ForecastFactory
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
        forecastMapper = ForecastMapper()
    }

    @Test
    fun mapToViewMapsData() {
        val forecastView = ForecastFactory.makeForecastView()
        val forecastViewModel = forecastMapper.mapToViewModel(forecastView)

        assertEquals(forecastView.icon, forecastViewModel.icon)
        assertEquals(forecastView.tempMax, forecastViewModel.tempMax)
        assertEquals(forecastView.tempMin, forecastViewModel.tempMin)
        assertEquals(forecastView.date, forecastViewModel.date)
        assertEquals(forecastView.dateTxt, forecastViewModel.dateTxt)
        assertEquals(forecastView.description, forecastViewModel.description)
    }

}