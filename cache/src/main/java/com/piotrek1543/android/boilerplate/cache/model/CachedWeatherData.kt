package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.WeatherDataConstants

/**
 * Model used solely for the caching of a WeatherData
 */
@Entity(tableName = WeatherDataConstants.TABLE_NAME)
data class CachedWeatherData(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        //var cachedCity: CachedCity? = null,
        var cod: String? = null,
        var message: Double? = null,
        var cnt: Int? = null
        // var cachedList: kotlin.collections.List<CachedList>? = null
)