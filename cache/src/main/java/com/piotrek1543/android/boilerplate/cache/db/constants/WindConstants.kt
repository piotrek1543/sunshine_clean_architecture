package com.piotrek1543.android.boilerplate.cache.db.constants

/**
 * Defines constants for the Wind Table
 */
object WindConstants {

    const val TABLE_NAME = "wind"

    const val QUERY_WIND = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_WINDS = "DELETE FROM $TABLE_NAME"

}