package com.piotrek1543.android.boilerplate.presentation.mapper

import com.piotrek1543.android.boilerplate.domain.model.List

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 *
 * @param <V> the view model input type
 * @param <D> the domain model output type
 */
interface Mapper<out V, in D> {

    fun mapToView(type: List): V

}