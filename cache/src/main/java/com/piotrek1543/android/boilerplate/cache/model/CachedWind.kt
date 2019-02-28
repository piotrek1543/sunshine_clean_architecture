package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.WindConstants

/**
 * Model used solely for the caching of a [Wind]
 */
@Entity(tableName = WindConstants.TABLE_NAME,
        foreignKeys = [ForeignKey(entity = CachedClouds::class,
                parentColumns = ["listDt"],
                childColumns = ["listDt"],
                onDelete = ForeignKey.CASCADE)]
)
data class CachedWind(
        @PrimaryKey
        val listDt: Long? = null,
        var speed: Double? = null,
        var deg: Float? = null
)