package com.piotrek1543.android.boilerplate.ui.model

/**
 * Representation for a [ForecastViewModel] fetched from an external layer data source
 */
data class ForecastViewModel(
        val date: Long,
        val dateTxt: String,
        val description: String,
        val tempMin: Double,
        val tempMax: Double,
        val icon: Int)