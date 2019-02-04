package org.buffer.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model used solely for the caching of a [Clouds]
 */
@Entity(tableName = "city")
data class CachedClouds(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var all: Int? = null)
