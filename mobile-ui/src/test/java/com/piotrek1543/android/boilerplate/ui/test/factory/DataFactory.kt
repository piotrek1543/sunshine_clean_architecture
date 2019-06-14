package com.piotrek1543.android.boilerplate.ui.test.factory

import com.piotrek1543.android.boilerplate.domain.model.*
import com.piotrek1543.android.boilerplate.domain.model.List

/**
 * Factory class for data instances
 */
class DataFactory {

    companion object Factory {
        private const val listDt: Long = 1560513600
        private val weather = Weather(
                id = 800,
                main = "Clear",
                description = "clear sky",
                icon = "01d",
                listDt = listDt
        )
        private val main = Main(
                temp = 27.73,
                tempMin = 25.68,
                tempMax = 27.73,
                pressure = "1020.23",
                seaLevel = "1020.23",
                grndLevel = "1009.65",
                humidity = 50,
                tempKf = "2.05",
                listDt = listDt
        )
        private val wind = Wind(speed = 1.31, deg = 63.317.toFloat(), listDt = listDt)
        private val rain = Rain(_3h = 0.0, listDt = listDt)
        private val snow = Snow(_3h = 0.0, listDt = listDt)
        private val clouds = Clouds(all = 0, listDt = listDt)
        private val pod = Pod(pod = "d", listDt = listDt)
        private val list = List(
                dt = listDt,
                main = main,
                weather = weather,
                clouds = clouds,
                wind = wind,
                rain = rain,
                snow = snow,
                pod = pod,
                dtTxt = "2019-06-14 12:00:00"
        )
        private val coord = Coord(lat = 52.4064, lon = 16.931992)
        private val city = City(
                id = 1000,
                name = "Poznan",
                country = "Poland",
                population = 1000000,
                coord = coord
        )
        private val forecast = Forecast(
                city = city,
                cod = "200",
                message = 0.0069,
                cnt = 40,
                list = listOf(list)
        )

        fun makeForecast() = forecast

    }

}