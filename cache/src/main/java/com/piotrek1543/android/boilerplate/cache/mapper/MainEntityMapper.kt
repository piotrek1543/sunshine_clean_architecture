package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedMain
import com.piotrek1543.android.boilerplate.data.model.MainEntity
import javax.inject.Inject

/**
 * Map a [CachedMain] instance to and from a [MainEntity] instance when data is moving between
 * this later and the Data layer
 */
open class MainEntityMapper @Inject constructor() :
        EntityMapper<CachedMain, MainEntity> {

    /**
     * Map a [MainEntity] instance to a [CachedMain] instance
     */
    override fun mapToCached(type: MainEntity): CachedMain = CachedMain(
            temp = type.temp,
            tempMin = type.tempMin,
            tempMax = type.tempMax,
            pressure = type.pressure,
            seaLevel = type.seaLevel,
            grndLevel = type.grndLevel,
            humidity = type.humidity,
            tempKf = type.tempKf
    )

    /**
     * Map a [CachedMain] instance to a [MainEntity] instance
     */
    override fun mapFromCached(type: CachedMain): MainEntity = MainEntity(
            temp = type.temp,
            tempMin = type.tempMin,
            tempMax = type.tempMax,
            pressure = type.pressure,
            seaLevel = type.seaLevel,
            grndLevel = type.grndLevel,
            humidity = type.humidity,
            tempKf = type.tempKf
    )

}