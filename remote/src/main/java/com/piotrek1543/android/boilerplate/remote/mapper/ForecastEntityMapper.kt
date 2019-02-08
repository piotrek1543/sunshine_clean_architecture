package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.remote.model.ForecastModel
import javax.inject.Inject

/**
 * Map a [ForecastModel] to and from a [ForecastEntity] instance when data is moving between
 * this later and the Data layer
 */
open class ForecastEntityMapper @Inject constructor(
        private val cityEntityMapper: CityEntityMapper,
        val listEntityMapper: ListEntityMapper
) : EntityMapper<ForecastModel, ForecastEntity> {

    /**
     * Map an instance of a [ForecastModel] to a [ForecastEntity] model
     */
    override fun mapFromRemote(type: ForecastModel): ForecastEntity {
        return ForecastEntity(
                cod = type.cod,
                cnt = type.cnt,
                message = type.message,
                cityEntity = type.city?.let { cityEntityMapper.mapFromRemote(it) },
                listEntity = type.list?.map { listModel -> let { listEntityMapper.mapFromRemote(listModel) } }
        )
    }

}