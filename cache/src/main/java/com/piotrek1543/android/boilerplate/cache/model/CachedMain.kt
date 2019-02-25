package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.MainConstants

/**
 * Model used solely for the caching of a [Main]
 */
@Entity(tableName = MainConstants.TABLE_NAME)
data class CachedMain(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var temp: Double? = null,
        var tempMin: Double? = null,
        var tempMax: Double? = null,
        var pressure: String? = null,
        var seaLevel: String? = null,
        var grndLevel: String? = null,
        var humidity: Int? = null,
        var tempKf: String? = null
)
