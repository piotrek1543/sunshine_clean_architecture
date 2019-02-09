package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.BufferooEntity
import com.piotrek1543.android.boilerplate.domain.model.Bufferoo
import javax.inject.Inject


/**
 * Map a [BufferooEntity] to and from a [Bufferoo] instance when data is moving between
 * this later and the Domain layer
 */
open class BufferooMapper @Inject constructor() : Mapper<BufferooEntity, Bufferoo> {

    /**
     * Map a [BufferooEntity] instance to a [Bufferoo] instance
     */
    override fun mapFromEntity(type: BufferooEntity): Bufferoo = Bufferoo(type.id, type.name, type.title, type.avatar)

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: Bufferoo): BufferooEntity = BufferooEntity(type.id, type.name, type.title, type.avatar)


}