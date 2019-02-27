package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.SnowEntity
import com.piotrek1543.android.boilerplate.domain.model.Snow
import javax.inject.Inject


/**
 * Map a [SnowEntity] to and from a [Snow] instance when data is moving between
 * this later and the Domain layer
 */
open class SnowMapper @Inject constructor() : Mapper<SnowEntity, Snow> {

    /**
     * Map a [SnowEntity] instance to a [Snow] instance
     */
    override fun mapFromEntity(type: SnowEntity): Snow = Snow(_3h = type._3h)

    /**
     * Map a [Snow] instance to a [SnowEntity] instance
     */
    override fun mapToEntity(type: Snow): SnowEntity = SnowEntity(_3h = type._3h, listDt = type.listDt)


}