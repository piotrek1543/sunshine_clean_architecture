package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.MainEntity
import com.piotrek1543.android.boilerplate.domain.model.Main
import javax.inject.Inject


/**
 * Map a [MainEntity] to and from a [Main] instance when data is moving between
 * this later and the Domain layer
 */
open class MainMapper @Inject constructor() : Mapper<MainEntity, Main> {

    /**
     * Map a [MainEntity] instance to a [Main] instance
     */
    override fun mapFromEntity(type: MainEntity): Main = Main(
            temp = type.temp,
            tempMin = type.tempMin,
            tempMax = type.tempMax,
            pressure = type.pressure,
            seaLevel = type.seaLevel,
            grndLevel = type.grndLevel,
            humidity = type.humidity,
            tempKf = type.tempKf,
            listDt = type.listDt
    )

    /**
     * Map a [Main] instance to a [MainEntity] instance
     */
    override fun mapToEntity(type: Main): MainEntity = MainEntity(
            temp = type.temp,
            tempMin = type.tempMin,
            tempMax = type.tempMax,
            pressure = type.pressure,
            seaLevel = type.seaLevel,
            grndLevel = type.grndLevel,
            humidity = type.humidity,
            tempKf = type.tempKf,
            listDt = type.listDt
    )


}