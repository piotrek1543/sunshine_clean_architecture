package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model used solely for the caching of a [City]
 */
@Entity(tableName = "city")
data class CachedCity(
        @PrimaryKey
        var id: Int,
        var name: String,
        var cachedCoord: CachedCoord? = null,
        var country: String? = null,
        var population: Int? = null,
        var cachedSys: CachedSys? = null
)
