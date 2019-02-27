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
    override fun mapFromRemote(type: ListModel): ListEntity {
        val mainModel = type.mainModel.apply { this?.listDt = type.dt }
        val weatherModel = type.weatherModel?.get(0)?.let { it.apply { this.listDt = type.dt } }
        val cloudsModel = type.cloudsModel.apply { this?.listDt = type.dt }
        val windModel = type.windModel.apply { this?.listDt = type.dt }
        val rainModel = type.rainModel.apply { this?.listDt = type.dt }
        val snowModel = type.snowModel.apply { this?.listDt = type.dt }
        val podModel = type.podModel.apply { this?.listDt = type.dt }
        return ListEntity(
                dt = type.dt,
                mainEntity = mainModel?.let { mainEntityMapper.mapFromRemote(it) },
                weatherEntity = weatherModel?.let { weatherEntityMapper.mapFromRemote(it) },
                cloudsEntity = cloudsModel?.let { cloudsEntityMapper.mapFromRemote(it) },
                windEntity = windModel?.let { windEntityMapper.mapFromRemote(it) },
                rainEntity = rainModel?.let { rainEntityMapper.mapFromRemote(it) },
                snowEntity = snowModel?.let { snowEntityMapper.mapFromRemote(it) },
                podEntity = podModel?.let { podEntityMapper.mapFromRemote(it) },
                dtTxt = type.dtTxt
        )
    }

}