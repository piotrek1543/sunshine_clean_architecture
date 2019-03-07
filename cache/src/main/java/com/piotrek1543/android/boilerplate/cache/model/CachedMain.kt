package com.piotrek1543.android.boilerplate.cache.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.MainConstants

/**
 * Model used solely for the caching of a [Main]
 */
@Entity(tableName = MainConstants.TABLE_NAME,
        foreignKeys = [ForeignKey(entity = CachedList::class,
                parentColumns = ["dt"],
                childColumns = ["listDt"],
                onDelete = CASCADE)])
data class CachedMain(
        @PrimaryKey
        var listDt: Long? = null,
        var temp: Double? = null,
        var tempMin: Double? = null,
        var tempMax: Double? = null,
        var pressure: String? = null,
        var seaLevel: String? = null,
        var grndLevel: String? = null,
        var humidity: Int? = null,
        var tempKf: String? = null
)
