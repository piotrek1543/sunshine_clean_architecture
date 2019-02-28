package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedClouds
import com.piotrek1543.android.boilerplate.data.model.CloudsEntity
import javax.inject.Inject

/**
 * Map a [CachedClouds] instance to and from a [CloudsEntity] instance when data is moving between
 * this later and the Data layer
 */
open class CloudsEntityMapper @Inject constructor() :
        EntityMapper<CachedClouds, CloudsEntity> {

    /**
     * Map a [CloudsEntity] instance to a [CachedClouds] instance
     */
    override fun mapToCached(type: CloudsEntity): CachedClouds = CachedClouds(all = type.all, listDt = type.listDt)

    /**
     * Map a [CachedClouds] instance to a [CloudsEntity] instance
     */
    override fun mapFromCached(type: CachedClouds): CloudsEntity = CloudsEntity(all = type.all, listDt = type.listDt)

}