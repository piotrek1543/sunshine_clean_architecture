package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.CityEntity
import com.piotrek1543.android.boilerplate.domain.model.City
import javax.inject.Inject


/**
 * Map a [CityEntity] to and from a [City] instance when data is moving between
 * this later and the Domain layer
 */
open class CityMapper @Inject constructor(private val coordMapper: CoordMapper) : Mapper<CityEntity, City> {

    /**
     * Map a [CityEntity] instance to a [City] instance
     */
    override fun mapFromEntity(type: CityEntity): City = City(id = type.id,
            name = type.name,
            country = type.country,
            population = type.population,
            coord = type.coordEntity?.let { coordMapper.mapFromEntity(it) }
    )

    /**
     * Map a [City] instance to a [CityEntity] instance
     */
    override fun mapToEntity(type: City): CityEntity {
        return CityEntity(
                id = type.id,
                name = type.name,
                country = type.country,
                population = type.population,
                coordEntity = type.coord?.let { coordMapper.mapToEntity(it) }
        )
    }

}