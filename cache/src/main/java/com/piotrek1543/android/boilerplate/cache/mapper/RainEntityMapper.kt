package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedRain
import com.piotrek1543.android.boilerplate.data.model.RainEntity
import javax.inject.Inject

/**
 * Map a [CachedRain] instance to and from a [RainEntity] instance when data is moving between
 * this later and the Data layer
 */
open class RainEntityMapper @Inject constructor() :
        EntityMapper<CachedRain, RainEntity> {

    /**
     * Map a [RainEntity] instance to a [CachedRain] instance
     */
    override fun mapToCached(type: RainEntity): CachedRain = CachedRain(_3h = type._3h)

    /**
     * Map a [CachedRain] instance to a [RainEntity] instance
     */
    override fun mapFromCached(type: CachedRain): RainEntity = RainEntity(_3h = type._3h)

}