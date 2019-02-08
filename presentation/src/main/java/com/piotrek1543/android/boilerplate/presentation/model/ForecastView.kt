package com.piotrek1543.android.boilerplate.presentation.model

import com.piotrek1543.android.boilerplate.domain.model.City

/**
 * Representation for a [ForecastView] instance for this layers Model representation
 */
class ForecastView(val cod: String? = null,
                   val message: Double? = null,
                   val cnt: Int? = null,
                   val city: City? = null)