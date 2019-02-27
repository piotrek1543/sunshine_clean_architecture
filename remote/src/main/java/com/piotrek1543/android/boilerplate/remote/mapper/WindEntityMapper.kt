package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.WindEntity
import com.piotrek1543.android.boilerplate.remote.model.WindModel
import javax.inject.Inject

/**
 * Map a [WindModel] to and from a [WindEntity] instance when data is moving between
 * this later and the Data layer
 */
open class WindEntityMapper @Inject constructor() : EntityMapper<WindModel, WindEntity> {

    /**
     * Map an instance of a [WindModel] to a [WindEntity] model
     */
    override fun mapFromRemote(type: WindModel): WindEntity = WindEntity(
            speed = type.speed,
            deg = type.deg,
            listDt = type.listDt
    )

}