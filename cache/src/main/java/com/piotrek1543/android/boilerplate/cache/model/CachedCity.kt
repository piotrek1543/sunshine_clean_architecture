package com.piotrek1543.android.boilerplate.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.CityConstants

/**
 * Model used solely for the caching of a [City]
 */
@Entity(tableName = CityConstants.TABLE_NAME)
data class CachedCity(
        @PrimaryKey
        var id: Int = 1000,
        var name: String? = null,
        //var cachedCoord: CachedCoord? = null,
        var country: String? = null,
        var population: Int? = null
)
