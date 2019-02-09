package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.RainEntity
import com.piotrek1543.android.boilerplate.remote.model.RainModel
import javax.inject.Inject

/**
 * Map a [RainModel] to and from a [RainEntity] instance when data is moving between
 * this later and the Data layer
 */
open class RainEntityMapper @Inject constructor() : EntityMapper<RainModel, RainEntity> {

    /**
     * Map an instance of a [RainModel] to a [RainEntity] model
     */
    override fun mapFromRemote(type: RainModel): RainEntity = RainEntity(_3h = type._3h)

}