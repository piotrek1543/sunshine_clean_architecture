package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.WindConstants

/**
 * Model used solely for the caching of a [Wind]
 */
@Entity(tableName = WindConstants.TABLE_NAME)
data class CachedWind(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var speed: Double? = null,
        var deg: Float? = null
)