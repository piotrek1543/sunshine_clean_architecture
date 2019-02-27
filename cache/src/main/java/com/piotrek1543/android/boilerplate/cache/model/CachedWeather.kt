package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.WeatherConstants


/**
 * Model used solely for the caching of a [Weather]
 */
@Entity(tableName = WeatherConstants.TABLE_NAME,
        foreignKeys = [ForeignKey(entity = CachedMain::class,
                parentColumns = ["listDt"],
                childColumns = ["listDt"],
                onDelete = ForeignKey.CASCADE)]
)
data class CachedWeather(
        @PrimaryKey
        var listDt: Long? = null,
        var id: Int? = null,
        var main: String? = null,
        var description: String? = null,
        var icon: String? = null
)
