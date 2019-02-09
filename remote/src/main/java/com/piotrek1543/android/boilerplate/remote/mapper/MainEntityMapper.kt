package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.MainEntity
import com.piotrek1543.android.boilerplate.remote.model.MainModel
import javax.inject.Inject

/**
 * Map a [MainModel] to and from a [MainEntity] instance when data is moving between
 * this later and the Data layer
 */
open class MainEntityMapper @Inject constructor() : EntityMapper<MainModel, MainEntity> {

    /**
     * Map an instance of a [MainModel] to a [MainEntity] model
     */
    override fun mapFromRemote(type: MainModel): MainEntity = MainEntity(
            temp = type.temp,
            tempMin = type.tempMin,
            tempMax = type.tempMax,
            pressure = type.pressure,
            seaLevel = type.seaLevel,
            grndLevel = type.grndLevel,
            humidity = type.humidity,
            tempKf = type.tempKf
    )

}