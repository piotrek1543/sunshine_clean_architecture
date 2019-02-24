package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.WeatherConstants


/**
 * Model used solely for the caching of a [Weather]
 */
@Entity(tableName = WeatherConstants.TABLE_NAME)
data class CachedWeather(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = 0,
        var main: String? = null,
        var description: String? = null,
        var icon: String? = null
)
