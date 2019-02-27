package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedSnow
import com.piotrek1543.android.boilerplate.data.model.SnowEntity
import javax.inject.Inject

/**
 * Map a [CachedSnow] instance to and from a [SnowEntity] instance when data is moving between
 * this later and the Data layer
 */
open class SnowEntityMapper @Inject constructor() :
        EntityMapper<CachedSnow, SnowEntity> {

    /**
     * Map a [SnowEntity] instance to a [CachedSnow] instance
     */
    override fun mapToCached(type: SnowEntity): CachedSnow = CachedSnow(_3h = type._3h)

    /**
     * Map a [CachedSnow] instance to a [SnowEntity] instance
     */
    override fun mapFromCached(type: CachedSnow): SnowEntity = SnowEntity(_3h = type._3h, listDt = type.listDt)

}