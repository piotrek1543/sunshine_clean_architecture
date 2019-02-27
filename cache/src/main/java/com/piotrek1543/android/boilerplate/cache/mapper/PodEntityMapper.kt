package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedPod
import com.piotrek1543.android.boilerplate.data.model.PodEntity
import javax.inject.Inject

/**
 * Map a [CachedPod] instance to and from a [PodEntity] instance when data is moving between
 * this later and the Data layer
 */
open class PodEntityMapper @Inject constructor() :
        EntityMapper<CachedPod, PodEntity> {

    /**
     * Map a [PodEntity] instance to a [CachedPod] instance
     */
    override fun mapToCached(type: PodEntity): CachedPod = CachedPod(pod = type.pod)

    /**
     * Map a [CachedPod] instance to a [PodEntity] instance
     */
    override fun mapFromCached(type: CachedPod): PodEntity = PodEntity(pod = type.pod, listDt = type.listDt)

}