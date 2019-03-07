package com.piotrek1543.android.boilerplate.ui.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.piotrek1543.android.boilerplate.presentation.ViewModelFactory
import com.piotrek1543.android.boilerplate.presentation.browse.GetForecastViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * Annotation class to identify view models by classname.
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

/**
 * Module that provides all dependencies from the presentation package/layer.
 */
@Module
abstract class PresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(GetForecastViewModel::class)
    abstract fun bindGetForecastViewModel(viewModel: GetForecastViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

