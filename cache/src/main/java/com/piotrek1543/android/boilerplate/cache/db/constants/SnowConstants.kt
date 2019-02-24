package com.piotrek1543.android.boilerplate.cache.db.constants

/**
 * Defines constants for the Snow Table
 */
object SnowConstants {

    const val TABLE_NAME = "snow"

    const val QUERY_SNOW = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_SNOWS = "DELETE FROM $TABLE_NAME"

}