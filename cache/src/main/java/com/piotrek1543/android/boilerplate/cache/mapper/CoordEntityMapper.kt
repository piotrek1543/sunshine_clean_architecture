package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedCoord
import com.piotrek1543.android.boilerplate.data.model.CoordEntity
import javax.inject.Inject

/**
 * Map a [CachedCoord] instance to and from a [CoordEntity] instance when data is moving between
 * this later and the Data layer
 */
open class CoordEntityMapper @Inject constructor() :
        EntityMapper<CachedCoord, CoordEntity> {

    /**
     * Map a [CoordEntity] instance to a [CachedCoord] instance
     */
    override fun mapToCached(type: CoordEntity) = CachedCoord(lat = type.lat, lon = type.lon)

    /**
     * Map a [CachedCoord] instance to a [CoordEntity] instance
     */
    override fun mapFromCached(type: CachedCoord): CoordEntity = CoordEntity(lat = type.lat, lon = type.lon)

}