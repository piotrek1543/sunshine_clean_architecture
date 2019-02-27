package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.CloudsEntity
import com.piotrek1543.android.boilerplate.domain.model.Clouds
import javax.inject.Inject


/**
 * Map a [CloudsEntity] to and from a [Clouds] instance when data is moving between
 * this later and the Domain layer
 */
open class CloudsMapper @Inject constructor() : Mapper<CloudsEntity, Clouds> {

    /**
     * Map a [CloudsEntity] instance to a [Clouds] instance
     */
    override fun mapFromEntity(type: CloudsEntity): Clouds = Clouds(all = type.all)

    /**
     * Map a [Clouds] instance to a [CloudsEntity] instance
     */
    override fun mapToEntity(type: Clouds): CloudsEntity = CloudsEntity(all = type.all, listDt = type.listDt)

}