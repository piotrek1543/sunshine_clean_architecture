package com.piotrek1543.android.boilerplate.cache.db.constants

/**
 * Defines constants for the Main Table
 */
object MainConstants {

    const val TABLE_NAME = "main"

    const val QUERY_MAIN = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_MAINS = "DELETE FROM $TABLE_NAME"

}