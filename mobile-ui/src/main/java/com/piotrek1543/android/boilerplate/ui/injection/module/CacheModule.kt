package com.piotrek1543.android.boilerplate.ui.injection.module

import android.app.Application
import androidx.room.Room
import com.piotrek1543.android.boilerplate.cache.ForecastCacheImpl
import com.piotrek1543.android.boilerplate.cache.db.SunshineDatabase
import com.piotrek1543.android.boilerplate.data.repository.ForecastCache
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
    abstract fun bindForecastCache(forecastCacheImpl: ForecastCacheImpl): ForecastCache
}
