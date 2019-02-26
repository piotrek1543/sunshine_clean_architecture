package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.ListEntity
import com.piotrek1543.android.boilerplate.domain.model.List
import javax.inject.Inject


/**
 * Map a [ListEntity] to and from a [List] instance when data is moving between
 * this later and the Domain layer
 */
open class ListMapper @Inject constructor(
        private val mainMapper: MainMapper,
        private val weatherMapper: WeatherMapper,
        private val cloudsMapper: CloudsMapper,
        private val windMapper: WindMapper,
        private val rainMapper: RainMapper,
        private val snowMapper: SnowMapper,
        private val podMapper: PodMapper
) : Mapper<ListEntity, List> {

    /**
     * Map a [ListEntity] instance to a [List] instance
     */
    override fun mapFromEntity(type: ListEntity): List = List(
            dt = type.dt,
            weather = type.weatherEntity?.let { weatherMapper.mapFromEntity(it) },
            main = type.mainEntity?.let { mainMapper.mapFromEntity(it) },
            // clouds = type.cloudsEntity?.let { cloudsMapper.mapFromEntity(it) },
            wind = type.windEntity?.let { windMapper.mapFromEntity(it) },
            rain = type.rainEntity?.let { rainMapper.mapFromEntity(it) },
            snow = type.snowEntity?.let { snowMapper.mapFromEntity(it) },
            pod = type.podEntity?.let { podMapper.mapFromEntity(it) },
            dtTxt = type.dtTxt
    )

    /**
     * Map a [List] instance to a [ListEntity] instance
     */
    override fun mapToEntity(type: List): ListEntity = ListEntity(
            dt = type.dt,
            weatherEntity = type.weather?.let { weatherMapper.mapToEntity(it) },
            mainEntity = type.main?.let { mainMapper.mapToEntity(it) },
            cloudsEntity = type.clouds?.let { cloudsMapper.mapToEntity(it) },
            windEntity = type.wind?.let { windMapper.mapToEntity(it) },
            rainEntity = type.rain?.let { rainMapper.mapToEntity(it) },
            snowEntity = type.snow?.let { snowMapper.mapToEntity(it) },
            podEntity = type.pod?.let { podMapper.mapToEntity(it) },
            dtTxt = type.dtTxt
    )

}