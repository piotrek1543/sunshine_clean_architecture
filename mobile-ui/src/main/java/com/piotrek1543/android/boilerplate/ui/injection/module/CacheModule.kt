package com.piotrek1543.android.boilerplate.ui.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import com.piotrek1543.android.boilerplate.cache.BufferooCacheImpl
import com.piotrek1543.android.boilerplate.cache.WeatherDataCacheImpl
import com.piotrek1543.android.boilerplate.cache.db.SunshineDatabase
import com.piotrek1543.android.boilerplate.data.repository.BufferooCache
import com.piotrek1543.android.boilerplate.data.repository.WeatherDataCache
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Module that provides all dependencies from the cache package/layer.
 */
@Module
abstract class CacheModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideSunshineDatabase(application: Application): SunshineDatabase {
            return Room.databaseBuilder(
                    application.applicationContext,
                    SunshineDatabase::class.java, "sunshine.db")
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

    @Binds
    abstract fun bindBufferooCache(bufferooCacheImpl: BufferooCacheImpl): BufferooCache

    @Binds
    abstract fun bindWeatherDataCache(weatherDataCacheImple: WeatherDataCacheImpl): WeatherDataCache
}
