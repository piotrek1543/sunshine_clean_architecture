package com.piotrek1543.android.boilerplate.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.piotrek1543.android.boilerplate.cache.dao.CachedBufferooDao
import com.piotrek1543.android.boilerplate.cache.dao.CachedForecastDao
import com.piotrek1543.android.boilerplate.cache.model.CachedBufferoo
import com.piotrek1543.android.boilerplate.cache.model.CachedForecast
import javax.inject.Inject

@Database(entities = [CachedBufferoo::class, CachedForecast::class], version = 2)
abstract class SunshineDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedBufferooDao(): CachedBufferooDao

    abstract fun cachedForecastDao(): CachedForecastDao

}