package com.piotrek1543.android.boilerplate.cache.db.constants

/**
 * Defines constants for the Rain Table
 */
object RainConstants {

    const val TABLE_NAME = "rain"

    const val QUERY_FORECAST = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_FORECASTS = "DELETE FROM $TABLE_NAME"

}