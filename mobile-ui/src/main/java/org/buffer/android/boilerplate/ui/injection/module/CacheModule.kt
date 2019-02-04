package org.buffer.android.boilerplate.ui.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.buffer.android.boilerplate.cache.BufferooCacheImpl
import org.buffer.android.boilerplate.cache.WeatherDataCacheImpl
import org.buffer.android.boilerplate.cache.db.SunshineDatabase
import org.buffer.android.boilerplate.data.repository.BufferooCache
import org.buffer.android.boilerplate.data.repository.WeatherDataCache

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
        fun provideBufferoosDatabase(application: Application): SunshineDatabase {
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
