package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedCity
import com.piotrek1543.android.boilerplate.data.model.CityEntity
import java.util.*
import javax.inject.Inject

/**
 * Map a [CachedCity] instance to and from a [CityEntity] instance when data is moving between
 * this later and the Data layer
 */
open class CityEntityMapper @Inject constructor() :
        EntityMapper<CachedCity, CityEntity> {

    /**
     * Map a [CityEntity] instance to a [CachedCity] instance
     */
    override fun mapToCached(type: CityEntity): CachedCity {
        return CachedCity(
                id = type.id ?: Random().nextInt(100_000),
                name = type.name,
                country = type.country,
                population = type.population
        )
    }

    /**
     * Map a [CachedCity] instance to a [CityEntity] instance
     */
    override fun mapFromCached(type: CachedCity): CityEntity {
        return CityEntity(
                id = type.id,
                name = type.name,
                country = type.country,
                population = type.population
        )
    }

}