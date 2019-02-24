package com.piotrek1543.android.boilerplate.cache.db.constants

/**
 * Defines constants for the Clouds Table
 */
object CloudsConstants {

    const val TABLE_NAME = "clouds"

    const val QUERY_CLOUDS = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_CLOUDS = "DELETE FROM $TABLE_NAME"

}