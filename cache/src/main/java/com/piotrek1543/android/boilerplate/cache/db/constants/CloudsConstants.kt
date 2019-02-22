package com.piotrek1543.android.boilerplate.cache.db.constants

/**
 * Defines constants for the Clouds Table
 */
object CloudsConstants {

    const val TABLE_NAME = "clouds"

    const val QUERY_FORECAST = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_FORECASTS = "DELETE FROM $TABLE_NAME"

}