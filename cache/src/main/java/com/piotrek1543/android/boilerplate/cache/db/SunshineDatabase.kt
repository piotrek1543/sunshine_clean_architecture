package com.piotrek1543.android.boilerplate.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.piotrek1543.android.boilerplate.cache.dao.*
import com.piotrek1543.android.boilerplate.cache.model.*
import javax.inject.Inject

@Database(
        entities = [
            CachedCity::class,
            CachedClouds::class,
            CachedCoord::class,
            CachedForecast::class,
            CachedList::class,
            CachedMain::class,
            CachedPod::class,
            CachedRain::class,
            CachedSnow::class,
            CachedWeather::class,
            CachedWind::class
        ],
        exportSchema = false,
        version = 2
)
abstract class SunshineDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedCityDao(): CachedCityDao

    abstract fun cachedCloudsDao(): CachedCloudsDao

    abstract fun cachedCoordDao(): CachedCoordDao

    abstract fun cachedForecastDao(): CachedForecastDao

    abstract fun cachedListDao(): CachedListDao

    abstract fun cachedMainDao(): CachedMainDao

    abstract fun cachedPodDao(): CachedPodDao

    abstract fun cachedRainDao(): CachedRainDao

    abstract fun cachedSnowDao(): CachedSnowDao

    abstract fun cachedWeatherDao(): CachedWeatherDao

    abstract fun cachedWindDao(): CachedWindDao

}