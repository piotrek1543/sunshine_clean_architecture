package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.CoordConstants


/**
 * Model used solely for the caching of a [Coord]
 */
@Entity(tableName = CoordConstants.TABLE_NAME)
data class CachedCoord(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = 0,
        var lon: Double? = null,
        var lat: Double? = null
)
