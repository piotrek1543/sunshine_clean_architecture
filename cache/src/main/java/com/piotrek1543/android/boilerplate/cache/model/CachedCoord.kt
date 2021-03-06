package com.piotrek1543.android.boilerplate.cache.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.CoordConstants


/**
 * Model used solely for the caching of a [Coord]
 */
@Entity(tableName = CoordConstants.TABLE_NAME,
        foreignKeys = [ForeignKey(entity = CachedCity::class,
                parentColumns = ["id"],
                childColumns = ["cityId"],
                onDelete = ForeignKey.CASCADE)]
)
data class CachedCoord(
        @PrimaryKey
        var cityId: Int? = null,
        var lon: Double? = null,
        var lat: Double? = null
)
