package com.piotrek1543.android.boilerplate.cache.db.constants

/**
 * Defines constants for the WeatherData Table
 */
object WeatherDataConstants {

    const val TABLE_NAME = "weather_data"

    const val QUERY_WEATHER_DATA = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_WEATHER_DATA = "DELETE FROM $TABLE_NAME"

}