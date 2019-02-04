package org.buffer.android.boilerplate.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import org.buffer.android.boilerplate.cache.dao.CachedBufferooDao
import org.buffer.android.boilerplate.cache.dao.CachedWeatherDataDao
import org.buffer.android.boilerplate.cache.model.CachedBufferoo
import org.buffer.android.boilerplate.cache.model.CachedWeatherData
import javax.inject.Inject

@Database(entities = [CachedBufferoo::class, CachedWeatherData::class], version = 2)
abstract class SunshineDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedBufferooDao(): CachedBufferooDao

    abstract fun cachedWeatherDataDao(): CachedWeatherDataDao

}