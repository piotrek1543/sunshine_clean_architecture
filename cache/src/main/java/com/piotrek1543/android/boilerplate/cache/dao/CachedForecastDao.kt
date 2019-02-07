package com.piotrek1543.android.boilerplate.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.ForecastConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedForecast

@Dao
abstract class CachedForecastDao {

    @Query(ForecastConstants.QUERY_FORECAST)
    abstract fun getForecast(): CachedForecast?

    @Query(ForecastConstants.DELETE_ALL_FORECASTS)
    abstract fun clearForecast()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertForecast(cachedForecast: CachedForecast)

}