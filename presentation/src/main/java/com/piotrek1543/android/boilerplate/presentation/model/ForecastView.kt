package com.piotrek1543.android.boilerplate.presentation.model

/**
 * Representation for a [ForecastView] instance for this layers Model representation
 */
class ForecastView(
        val date: String,
        val description: String,
        val tempMin: Double,
        val tempMax: Double,
        val icon: Int
)