package com.piotrek1543.android.boilerplate.ui.model

/**
 * Representation for a [ForecastViewModel] fetched from an external layer data source
 */
class ForecastViewModel(val date: String,
                        val description: String,
                        val tempMin: String,
                        val tempMax: String,
                        val icon: String)