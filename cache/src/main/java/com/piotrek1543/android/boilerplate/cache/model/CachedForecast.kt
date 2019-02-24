package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.ForecastConstants

/**
 * Model used solely for the caching of a Forecast
 */
@Entity(tableName = ForecastConstants.TABLE_NAME)
data class CachedForecast(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = 0,
        //var cachedCity: CachedCity? = null,
        var cod: String? = null,
        var message: Double? = null,
        var cnt: Int? = null
        // var cachedList: kotlin.collections.List<CachedList>? = null
)