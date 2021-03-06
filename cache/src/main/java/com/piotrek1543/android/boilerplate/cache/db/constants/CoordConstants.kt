package com.piotrek1543.android.boilerplate.cache.db.constants

/**
 * Defines constants for the Coord Table
 */
object CoordConstants {

    const val TABLE_NAME = "coord"

    const val QUERY_COORD = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_COORDS = "DELETE FROM $TABLE_NAME"

}