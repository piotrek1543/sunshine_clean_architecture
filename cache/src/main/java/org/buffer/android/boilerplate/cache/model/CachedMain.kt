package org.buffer.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model used solely for the caching of a [Main]
 */
@Entity(tableName = "main")
data class CachedMain(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var temp: Double? = null,
        var tempMin: Double? = null,
        var tempMax: Double? = null,
        var pressure: String? = null,
        var seaLevel: String? = null,
        var grndLevel: String? = null,
        var humidity: Int? = null,
        var tempKf: String? = null
)
