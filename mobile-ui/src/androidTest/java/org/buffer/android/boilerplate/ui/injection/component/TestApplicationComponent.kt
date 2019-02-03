package org.buffer.android.boilerplate.ui.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import org.buffer.android.boilerplate.ui.injection.ApplicationComponent
import org.buffer.android.boilerplate.ui.injection.module.*
import org.buffer.android.boilerplate.ui.test.TestApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        TestApplicationModule::class,
        AndroidSupportInjectionModule::class,
        TestCacheModule::class,
        TestRemoteModule::class,
        TestDataModule::class,
        PresentationModule::class,
        UiModule::class))
interface TestApplicationComponent : ApplicationComponent {

    fun bufferooRepository(): BufferooRepository

    fun postExecutionThread(): PostExecutionThread

    fun inject(application: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

}