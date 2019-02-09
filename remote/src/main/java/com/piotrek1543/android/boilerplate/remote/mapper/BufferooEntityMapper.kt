package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.BufferooEntity
import com.piotrek1543.android.boilerplate.remote.model.BufferooModel
import javax.inject.Inject

/**
 * Map a [BufferooModel] to and from a [BufferooEntity] instance when data is moving between
 * this later and the Data layer
 */
open class BufferooEntityMapper @Inject constructor() : EntityMapper<BufferooModel, BufferooEntity> {

    /**
     * Map an instance of a [BufferooModel] to a [BufferooEntity] model
     */
    override fun mapFromRemote(type: BufferooModel): BufferooEntity =
            BufferooEntity(type.id, type.name, type.title, type.avatar)

}