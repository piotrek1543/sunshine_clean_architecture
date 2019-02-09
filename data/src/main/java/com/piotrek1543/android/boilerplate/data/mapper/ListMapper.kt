package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.ListEntity
import com.piotrek1543.android.boilerplate.domain.model.List
import javax.inject.Inject


/**
 * Map a [ListEntity] to and from a [List] instance when data is moving between
 * this later and the Domain layer
 */
open class ListMapper @Inject constructor(
        private val cloudsMapper: CloudsMapper,
        private val rainMapper: RainMapper
) : Mapper<ListEntity, List> {

    /**
     * Map a [ListEntity] instance to a [List] instance
     */
    override fun mapFromEntity(type: ListEntity): List {
        return List(dt = type.dt,
                clouds = type.cloudsEntity?.let { cloudsMapper.mapFromEntity(it) },
                rain = type.rainEntity?.let { rainMapper.mapFromEntity(it) },
                dtTxt = type.dtTxt
        )
    }

    /**
     * Map a [List] instance to a [ListEntity] instance
     */
    override fun mapToEntity(type: List): ListEntity {
        return ListEntity(
                dt = type.dt,
                cloudsEntity = type.clouds?.let { cloudsMapper.mapToEntity(it) },
                rainEntity = type.rain?.let { rainMapper.mapToEntity(it) },
                dtTxt = type.dtTxt
        )
    }

}