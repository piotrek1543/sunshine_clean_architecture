package org.buffer.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model used solely for the caching of a [List]
 */
@Entity(tableName = "list")
data class CachedList(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var dt: Int? = null,
        var cachedMain: CachedMain? = null,
        var cachedWeather: kotlin.collections.List<CachedWeather>? = null,
        var cachedClouds: CachedClouds? = null,
        var cachedWind: CachedWind? = null,
        var cachedRain: CachedRain? = null,
        var cachedSnow: CachedSnow? = null,
        var cachedPod: CachedPod? = null,
        var dtTxt: String? = null
)
