package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.CityEntity
import com.piotrek1543.android.boilerplate.remote.model.CityModel
import javax.inject.Inject

/**
 * Map a [CityModel] to and from a [CityEntity] instance when data is moving between
 * this later and the Data layer
 */
open class CityEntityMapper @Inject constructor(private val coordEntityMapper: CoordEntityMapper) : EntityMapper<CityModel, CityEntity> {

    /**
     * Map an instance of a [CityModel] to a [CityEntity] model
     */
    override fun mapFromRemote(type: CityModel): CityEntity = CityEntity(
            id = type.id,
            name = type.name,
            country = type.country,
            population = type.population,
            coordEntity = type.coord?.let { coordEntityMapper.mapFromRemote(it) })

}