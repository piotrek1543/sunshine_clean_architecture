package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * Model used solely for the caching of a [Coord]
 */
@Entity(tableName = "coord")
data class CachedCoord(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var lon: Double? = null,
        var lat: Double? = null
)
