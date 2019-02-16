package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.ListEntity
import com.piotrek1543.android.boilerplate.remote.model.ListModel
import javax.inject.Inject

/**
 * Map a [ListModel] to and from a [ListEntity] instance when data is moving between
 * this later and the Data layer
 */
open class ListEntityMapper @Inject constructor(
        private val mainEntityMapper: MainEntityMapper,
        private val weatherEntityMapper: WeatherEntityMapper,
        private val cloudsEntityMapper: CloudsEntityMapper,
        private val windEntityMapper: WindEntityMapper,
        private val rainEntityMapper: RainEntityMapper,
        private val snowEntityMapper: SnowEntityMapper,
        private val podEntityMapper: PodEntityMapper
) : EntityMapper<ListModel, ListEntity> {

    /**
     * Map an instance of a [ListModel] to a [ListEntity] model
     */
    override fun mapFromRemote(type: ListModel): ListEntity = ListEntity(
            dt = type.dt,
            mainEntity = type.mainModel?.let { mainEntityMapper.mapFromRemote(it) },
            weatherEntity = type.weatherModel?.map { weatherModel ->
                weatherModel.let { weatherEntityMapper.mapFromRemote(it) }
            },
            cloudsEntity = type.cloudsModel?.let { cloudsEntityMapper.mapFromRemote(it) },
            windEntity = type.windModel?.let { windEntityMapper.mapFromRemote(it) },
            rainEntity = type.rainModel?.let { rainEntityMapper.mapFromRemote(it) },
            snowEntity = type.snowModel?.let { snowEntityMapper.mapFromRemote(it) },
            podEntity = type.podModel?.let { podEntityMapper.mapFromRemote(it) },
            dtTxt = type.dtTxt
    )

}