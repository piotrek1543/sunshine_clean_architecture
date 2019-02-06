package com.piotrek1543.android.boilerplate.ui.mapper

import com.piotrek1543.android.boilerplate.presentation.model.BufferooView
import com.piotrek1543.android.boilerplate.ui.model.BufferooViewModel
import javax.inject.Inject

/**
 * Map a [BufferooView] to and from a [BufferooViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class BufferooMapper @Inject constructor() : Mapper<BufferooViewModel, BufferooView> {

    /**
     * Map a [BufferooView] instance to a [BufferooViewModel] instance
     */
    override fun mapToViewModel(type: BufferooView): BufferooViewModel {
        return BufferooViewModel(type.name, type.title, type.avatar)
    }

}