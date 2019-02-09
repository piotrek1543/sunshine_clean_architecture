package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.WindEntity
import com.piotrek1543.android.boilerplate.domain.model.Wind
import javax.inject.Inject


/**
 * Map a [WindEntity] to and from a [Wind] instance when data is moving between
 * this later and the Domain layer
 */
open class WindMapper @Inject constructor() : Mapper<WindEntity, Wind> {

    /**
     * Map a [WindEntity] instance to a [Wind] instance
     */
    override fun mapFromEntity(type: WindEntity): Wind = Wind(speed = type.speed, deg = type.deg)

    /**
     * Map a [Wind] instance to a [WindEntity] instance
     */
    override fun mapToEntity(type: Wind): WindEntity = WindEntity(speed = type.speed, deg = type.deg)

}