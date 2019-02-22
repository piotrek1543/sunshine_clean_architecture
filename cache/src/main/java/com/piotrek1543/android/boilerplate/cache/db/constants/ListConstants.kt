package com.piotrek1543.android.boilerplate.cache.db.constants

/**
 * Defines constants for the List Table
 */
object ListConstants {

    const val TABLE_NAME = "list"

    const val QUERY_FORECAST = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_FORECASTS = "DELETE FROM $TABLE_NAME"

}