package com.piotrek1543.android.boilerplate.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.WeatherConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedWeather

@Dao
abstract class CachedWeatherDao {

    @Query(WeatherConstants.QUERY_WEATHER)
    abstract fun getWeather(): List<CachedWeather>?

    @Query(WeatherConstants.DELETE_ALL_WEATHERS)
    abstract fun clearWeather()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWeather(cachedWeather: List<CachedWeather>?)

}