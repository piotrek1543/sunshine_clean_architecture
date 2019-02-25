package com.piotrek1543.android.boilerplate.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.WeatherConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedWeather

@Dao
abstract class CachedWeatherDao {

    @Query(WeatherConstants.QUERY_WEATHER)
    abstract fun getWeather(): CachedWeather?

    @Query(WeatherConstants.DELETE_ALL_WEATHERS)
    abstract fun clearWeather()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWeather(cachedWeather: CachedWeather?)

}