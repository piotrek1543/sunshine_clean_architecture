package com.piotrek1543.android.boilerplate.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.piotrek1543.android.boilerplate.cache.dao.CachedForecastDao
import com.piotrek1543.android.boilerplate.cache.model.CachedForecast
import javax.inject.Inject

@Database(entities = [CachedForecast::class], version = 2)
abstract class SunshineDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedForecastDao(): CachedForecastDao

}