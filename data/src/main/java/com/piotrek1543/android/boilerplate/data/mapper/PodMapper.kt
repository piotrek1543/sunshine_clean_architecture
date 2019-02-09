package com.piotrek1543.android.boilerplate.data.mapper

import com.piotrek1543.android.boilerplate.data.model.PodEntity
import com.piotrek1543.android.boilerplate.domain.model.Pod
import javax.inject.Inject


/**
 * Map a [PodEntity] to and from a [Pod] instance when data is moving between
 * this later and the Domain layer
 */
open class PodMapper @Inject constructor() : Mapper<PodEntity, Pod> {

    /**
     * Map a [PodEntity] instance to a [Pod] instance
     */
    override fun mapFromEntity(type: PodEntity): Pod = Pod(pod = type.pod)

    /**
     * Map a [Pod] instance to a [PodEntity] instance
     */
    override fun mapToEntity(type: Pod): PodEntity = PodEntity(pod = type.pod)


}