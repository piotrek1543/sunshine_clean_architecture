package com.piotrek1543.android.boilerplate.ui.injection

import android.app.Application
import com.piotrek1543.android.boilerplate.ui.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    AndroidSupportInjectionModule::class,
    CacheModule::class,
    DataModule::class,
    DomainModule::class,
    PresentationModule::class,
    RemoteModule::class,
    UiModule::class]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

}
