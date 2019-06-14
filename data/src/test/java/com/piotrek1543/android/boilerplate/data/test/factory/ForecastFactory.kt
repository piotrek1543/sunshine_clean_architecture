package com.piotrek1543.android.boilerplate.data.test.factory

import com.piotrek1543.android.boilerplate.data.model.*
import com.piotrek1543.android.boilerplate.domain.model.Forecast

/**
 * Factory class for Forecast related instances
 */
class ForecastFactory {

    companion object Factory {
        private val forecast = DataFactory.makeForecast()

        fun makeForecastEntity(): ForecastEntity {
            val list = forecast.list!!.first()
            val city = forecast.city!!
            val coord = city.coord!!
            val weather = list.weather!!
            val main = list.main!!
            val rain = list.rain!!
            val clouds = list.clouds!!
            val snow = list.snow!!
            val pod = list.pod!!
            val wind = list.wind!!

            val coordEntity = CoordEntity(lat = coord.lat, lon = coord.lon, cityId = city.id)
            val cityEntity = CityEntity(
                    id = city.id,
                    name = city.name,
                    population = city.population,
                    country = city.country,
                    coordEntity = coordEntity
            )
            val weatherEntity = WeatherEntity(
                    id = weather.id,
                    main = weather.main,
                    description = weather.description,
                    icon = weather.icon,
                    listDt = list.dt
            )
            val mainEntity = MainEntity(
                    temp = main.temp,
                    tempMin = main.tempMin,
                    tempMax = main.tempMax,
                    pressure = main.pressure,
                    seaLevel = main.seaLevel,
                    grndLevel = main.grndLevel,
                    humidity = main.humidity,
                    tempKf = main.tempKf,
                    listDt = main.listDt
            )
            val cloudsEntity = CloudsEntity(all = clouds.all, listDt = list.dt)
            val windEntity = WindEntity(speed = wind.speed, deg = wind.deg, listDt = list.dt)
            val rainEntity = RainEntity(_3h = rain._3h, listDt = list.dt)
            val snowEntity = SnowEntity(_3h = snow._3h, listDt = list.dt)
            val podEntity = PodEntity(pod = pod.pod, listDt = list.dt)
            val listEntity = ListEntity(
                    dt = list.dt,
                    mainEntity = mainEntity,
                    weatherEntity = weatherEntity,
                    cloudsEntity = cloudsEntity,
                    windEntity = windEntity,
                    rainEntity = rainEntity,
                    snowEntity = snowEntity,
                    podEntity = podEntity,
                    dtTxt = list.dtTxt
            )

            return ForecastEntity(
                    cityEntity = cityEntity,
                    cod = forecast.cod,
                    message = forecast.message,
                    cnt = forecast.cnt,
                    listEntity = listOf(listEntity)
            )
        }

        fun makeForecast(): Forecast = forecast
    }

}