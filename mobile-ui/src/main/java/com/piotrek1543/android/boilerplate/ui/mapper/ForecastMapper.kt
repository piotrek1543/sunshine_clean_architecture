package com.piotrek1543.android.boilerplate.ui.mapper

import com.piotrek1543.android.boilerplate.presentation.model.ForecastView
import com.piotrek1543.android.boilerplate.ui.model.ForecastViewModel
import javax.inject.Inject

/**
 * Map a [ForecastView] to and from a [ForecastViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class ForecastMapper @Inject constructor() : Mapper<ForecastViewModel, ForecastView> {

    /**
     * Map a [ForecastView] instance to a [ForecastViewModel] instance
     */
    override fun mapToViewModel(type: ForecastView): ForecastViewModel =
            ForecastViewModel(type.temp, type.description)

}