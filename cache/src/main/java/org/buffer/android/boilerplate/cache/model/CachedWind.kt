package org.buffer.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model used solely for the caching of a [Wind]
 */
@Entity(tableName = "wind")
data class CachedWind(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var speed: Double? = null,
        var deg: Float? = null
)