package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.CityConstants

/**
 * Model used solely for the caching of a [City]
 */
@Entity(tableName = CityConstants.TABLE_NAME)
data class CachedCity(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var name: String? = null,
        //var cachedCoord: CachedCoord? = null,
        var country: String? = null,
        var population: Int? = null
)
