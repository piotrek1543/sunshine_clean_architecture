package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.SnowEntity
import com.piotrek1543.android.boilerplate.remote.model.SnowModel
import javax.inject.Inject

/**
 * Map a [SnowModel] to and from a [SnowEntity] instance when data is moving between
 * this later and the Data layer
 */
open class SnowEntityMapper @Inject constructor() : EntityMapper<SnowModel, SnowEntity> {

    /**
     * Map an instance of a [SnowModel] to a [SnowEntity] model
     */
    override fun mapFromRemote(type: SnowModel): SnowEntity = SnowEntity(_3h = type._3h, listDt = type.listDt)

}