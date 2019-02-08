package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.CoordEntity
import com.piotrek1543.android.boilerplate.remote.model.CoordModel
import javax.inject.Inject

/**
 * Map a [CoordModel] to and from a [CoordEntity] instance when data is moving between
 * this later and the Data layer
 */
open class CoordEntityMapper @Inject constructor() : EntityMapper<CoordModel, CoordEntity> {

    /**
     * Map an instance of a [CoordModel] to a [CoordEntity] model
     */
    override fun mapFromRemote(type: CoordModel): CoordEntity {
        return CoordEntity(lat = type.lat, lon = type.lon)
    }

}