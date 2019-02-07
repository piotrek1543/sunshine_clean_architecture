package com.piotrek1543.android.boilerplate.ui.injection.module

import com.piotrek1543.android.boilerplate.data.repository.BufferooRemote
import com.piotrek1543.android.boilerplate.data.repository.ForecastRemote
import com.piotrek1543.android.boilerplate.remote.*
import com.piotrek1543.android.boilerplate.ui.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module
abstract class RemoteModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideBufferooService(): BufferooService {
            return BufferooServiceFactory.makeBuffeoorService(BuildConfig.DEBUG)
        }

        @Provides
        @JvmStatic
        fun provideSunshineService(): SunshineService {
            return SunshineServiceFactory.makeSunshineService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindBufferooRemote(bufferooRemoteImpl: BufferooRemoteImpl): BufferooRemote

    @Binds
    abstract fun bindForecastRemote(forecastRemoteImpl: ForecastRemoteImpl): ForecastRemote
}