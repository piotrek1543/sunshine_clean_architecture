package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.ForecastEntity
import com.piotrek1543.android.boilerplate.domain.model.Forecast
import javax.inject.Inject


/**
 * Map a [ForecastEntity] to and from a [Forecast] instance when data is moving between
 * this later and the Domain layer
 */
open class ForecastMapper @Inject constructor() : Mapper<ForecastEntity, Forecast> {

    /**
     * Map a [ForecastEntity] instance to a [Forecast] instance
     */
    override fun mapFromEntity(type: ForecastEntity): Forecast {
        return Forecast(cod = type.cod, cnt = type.cnt, message = type.message)
    }

    /**
     * Map a [Forecast] instance to a [ForecastEntity] instance
     */
    override fun mapToEntity(type: Forecast): ForecastEntity {
        return ForecastEntity(cod = type.cod, cnt = type.cnt, message = type.message)
    }


}