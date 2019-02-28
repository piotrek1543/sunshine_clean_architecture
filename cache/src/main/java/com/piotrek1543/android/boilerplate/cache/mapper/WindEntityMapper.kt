package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedWind
import com.piotrek1543.android.boilerplate.data.model.WindEntity
import javax.inject.Inject

/**
 * Map a [CachedWind] instance to and from a [WindEntity] instance when data is moving between
 * this later and the Data layer
 */
open class WindEntityMapper @Inject constructor() :
        EntityMapper<CachedWind, WindEntity> {

    /**
     * Map a [WindEntity] instance to a [CachedWind] instance
     */
    override fun mapToCached(type: WindEntity): CachedWind = CachedWind(
            speed = type.speed,
            deg = type.deg,
            listDt = type.listDt
    )

    /**
     * Map a [CachedWind] instance to a [WindEntity] instance
     */
    override fun mapFromCached(type: CachedWind): WindEntity = WindEntity(
            speed = type.speed,
            deg = type.deg,
            listDt = type.listDt
    )

}