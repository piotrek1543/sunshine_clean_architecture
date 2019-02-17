package com.piotrek1543.android.boilerplate.ui.injection.module

import com.piotrek1543.android.boilerplate.data.ForecastDataRepository
import com.piotrek1543.android.boilerplate.data.executor.JobExecutor
import com.piotrek1543.android.boilerplate.domain.executor.ThreadExecutor
import com.piotrek1543.android.boilerplate.domain.repository.ForecastRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindForecastDataRepository(forecastDataDataRepository: ForecastDataRepository): ForecastRepository

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor
}