package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.ListEntity
import com.piotrek1543.android.boilerplate.remote.model.ListModel
import javax.inject.Inject

/**
 * Map a [ListModel] to and from a [ListEntity] instance when data is moving between
 * this later and the Data layer
 */
open class ListEntityMapper @Inject constructor(
        private val cloudsEntityMapper: CloudsEntityMapper,
        private val rainEntityMapper: RainEntityMapper
) : EntityMapper<ListModel, ListEntity> {

    /**
     * Map an instance of a [ListModel] to a [ListEntity] model
     */
    override fun mapFromRemote(type: ListModel): ListEntity {
        return ListEntity(
                dt = type.dt,
                cloudsEntity = type.cloudsModel?.let { cloudsEntityMapper.mapFromRemote(it) },
                rainEntity = type.rainModel?.let { rainEntityMapper.mapFromRemote(it) },
                dtTxt = type.dtTxt
        )
    }

}