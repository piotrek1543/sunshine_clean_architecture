package com.piotrek1543.android.boilerplate.ui.injection.module

import com.piotrek1543.android.boilerplate.data.BufferooDataRepository
import com.piotrek1543.android.boilerplate.data.WeatherDataDataRepository
import com.piotrek1543.android.boilerplate.data.executor.JobExecutor
import com.piotrek1543.android.boilerplate.domain.executor.ThreadExecutor
import com.piotrek1543.android.boilerplate.domain.repository.BufferooRepository
import com.piotrek1543.android.boilerplate.domain.repository.WeatherDataRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindBufferooRepository(bufferooDataRepository: BufferooDataRepository): BufferooRepository

    @Binds
    abstract fun bindWeatherDataDataRepository(weatherDataDataRepository: WeatherDataDataRepository): WeatherDataRepository

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor
}