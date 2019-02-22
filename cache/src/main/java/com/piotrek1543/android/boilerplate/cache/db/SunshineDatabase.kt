package com.piotrek1543.android.boilerplate.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.piotrek1543.android.boilerplate.cache.dao.CachedForecastDao
import com.piotrek1543.android.boilerplate.cache.model.*
import javax.inject.Inject

@Database(entities = [
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
], version = 2)
abstract class SunshineDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedForecastDao(): CachedForecastDao

}