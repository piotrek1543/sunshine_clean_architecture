package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.CoordEntity
import com.piotrek1543.android.boilerplate.domain.model.Coord
import javax.inject.Inject


/**
 * Map a [CoordEntity] to and from a [Coord] instance when data is moving between
 * this later and the Domain layer
 */
open class CoordMapper @Inject constructor() : Mapper<CoordEntity, Coord> {

    /**
     * Map a [CoordEntity] instance to a [Coord] instance
     */
    override fun mapFromEntity(type: CoordEntity): Coord {
        return Coord(lat = type.lat, lon = type.lon)
    }

    /**
     * Map a [Coord] instance to a [CoordEntity] instance
     */
    override fun mapToEntity(type: Coord): CoordEntity {
        return CoordEntity(lat = type.lat, lon = type.lon)
    }


}