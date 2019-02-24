package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.ListConstants

/**
 * Model used solely for the caching of a [List]
 */
@Entity(tableName = ListConstants.TABLE_NAME)
data class CachedList(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = 0,
        var dt: Long? = null,
       // var cachedMain: CachedMain? = null,
        //var cachedWeather: kotlin.collections.List<CachedWeather>? = null,
        //var cachedClouds: CachedClouds? = null,
       // var cachedWind: CachedWind? = null,
       // var cachedRain: CachedRain? = null,
       // var cachedSnow: CachedSnow? = null,
       // var cachedPod: CachedPod? = null,
        var dtTxt: String? = null
)
