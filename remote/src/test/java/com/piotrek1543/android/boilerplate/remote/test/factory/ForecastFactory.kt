package com.piotrek1543.android.boilerplate.remote.test.factory

import com.piotrek1543.android.boilerplate.remote.model.*

/**
 * Factory class for Forecast related instances
 */
class ForecastFactory {

    companion object Factory {
        private val forecast = DataFactory.makeForecast()


        fun makeForecastResponse(): ForecastModel {
            return makeForecastModel()
        }

        fun makeForecastModel(): ForecastModel {
            val list = ForecastFactory.forecast.list!!.first()
            val city = ForecastFactory.forecast.city!!
            val coord = city.coord!!
            val weather = list.weather!!
            val main = list.main!!
            val rain = list.rain!!
            val clouds = list.clouds!!
            val snow = list.snow!!
            val pod = list.pod!!
            val wind = list.wind!!

            val coordModel = CoordModel(lat = coord.lat, lon = coord.lon, cityId = city.id)
            val cityModel = CityModel(
                    id = city.id,
                    name = city.name,
                    population = city.population,
                    country = city.country,
                    coord = coordModel
            )
            val weatherModel = WeatherModel(
                    id = weather.id,
                    main = weather.main,
                    description = weather.description,
                    icon = weather.icon,
                    listDt = list.dt
            )
            val mainModel = MainModel(
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
            val cloudsModel = CloudsModel(all = clouds.all, listDt = list.dt)
            val windModel = WindModel(speed = wind.speed, deg = wind.deg, listDt = list.dt)
            val rainModel = RainModel(_3h = rain._3h, listDt = list.dt)
            val snowModel = SnowModel(_3h = snow._3h, listDt = list.dt)
            val podModel = PodModel(pod = pod.pod, listDt = list.dt)
            val listModel = ListModel(
                    dt = list.dt,
                    mainModel = mainModel,
                    weatherModel = listOf(weatherModel),
                    cloudsModel = cloudsModel,
                    windModel = windModel,
                    rainModel = rainModel,
                    snowModel = snowModel,
                    podModel = podModel,
                    dtTxt = list.dtTxt
            )

            return ForecastModel(
                    city = cityModel,
                    cod = ForecastFactory.forecast.cod,
                    message = ForecastFactory.forecast.message,
                    cnt = ForecastFactory.forecast.cnt,
                    list = listOf(listModel)
            )
        }
    }

}