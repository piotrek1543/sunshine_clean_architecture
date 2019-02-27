package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.PodEntity
import com.piotrek1543.android.boilerplate.remote.model.PodModel
import javax.inject.Inject

/**
 * Map a [PodModel] to and from a [PodEntity] instance when data is moving between
 * this later and the Data layer
 */
open class PodEntityMapper @Inject constructor() : EntityMapper<PodModel, PodEntity> {

    /**
     * Map an instance of a [PodModel] to a [PodEntity] model
     */
    override fun mapFromRemote(type: PodModel): PodEntity = PodEntity(pod = type.pod, listDt = type.listDt)

}