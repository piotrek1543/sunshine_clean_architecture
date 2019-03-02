package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.RainConstants

/**
 * Model used solely for the caching of a [Rain]
 */
@Entity(tableName = RainConstants.TABLE_NAME,
        foreignKeys = [ForeignKey(entity = CachedWind::class,
                parentColumns = ["listDt"],
                childColumns = ["listDt"],
                onDelete = ForeignKey.CASCADE)])
data class CachedRain(
        @PrimaryKey(autoGenerate = true)
        var listDt: Long? = null,
        var _3h: Double? = null
)
