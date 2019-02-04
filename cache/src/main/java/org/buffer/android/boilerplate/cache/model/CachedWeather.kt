package org.buffer.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * Model used solely for the caching of a [Weather]
 */
@Entity(tableName = "weather")
data class CachedWeather(
        @PrimaryKey
        var id: Int,
        var main: String? = null,
        var description: String? = null,
        var icon: String? = null
)
