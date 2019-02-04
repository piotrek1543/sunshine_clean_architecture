package org.buffer.android.boilerplate.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import org.buffer.android.boilerplate.cache.db.constants.WeatherDataConstants
import org.buffer.android.boilerplate.cache.model.CachedWeatherData

@Dao
abstract class CachedWeatherDataDao {

    @Query(WeatherDataConstants.QUERY_WEATHER_DATA)
    abstract fun getWeatherData(): CachedWeatherData

    @Query(WeatherDataConstants.DELETE_ALL_WEATHER_DATA)
    abstract fun clearWeatherData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWeatherData(cachedWeatherData: CachedWeatherData)

}