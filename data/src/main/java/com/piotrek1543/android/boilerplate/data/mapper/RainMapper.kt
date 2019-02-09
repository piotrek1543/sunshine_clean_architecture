package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.RainEntity
import com.piotrek1543.android.boilerplate.domain.model.Rain
import javax.inject.Inject


/**
 * Map a [RainEntity] to and from a [Rain] instance when data is moving between
 * this later and the Domain layer
 */
open class RainMapper @Inject constructor() : Mapper<RainEntity, Rain> {

    /**
     * Map a [RainEntity] instance to a [Rain] instance
     */
    override fun mapFromEntity(type: RainEntity): Rain = Rain(_3h = type._3h)

    /**
     * Map a [Rain] instance to a [RainEntity] instance
     */
    override fun mapToEntity(type: Rain): RainEntity = RainEntity(_3h = type._3h)


}