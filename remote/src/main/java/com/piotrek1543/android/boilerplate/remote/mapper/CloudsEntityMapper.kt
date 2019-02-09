package com.piotrek1543.android.boilerplate.remote.mapper

import com.piotrek1543.android.boilerplate.data.model.CloudsEntity
import com.piotrek1543.android.boilerplate.remote.model.CloudsModel
import javax.inject.Inject

/**
 * Map a [CloudsModel] to and from a [CloudsEntity] instance when data is moving between
 * this later and the Data layer
 */
open class CloudsEntityMapper @Inject constructor() : EntityMapper<CloudsModel, CloudsEntity> {

    /**
     * Map an instance of a [CloudsModel] to a [CloudsEntity] model
     */
    override fun mapFromRemote(type: CloudsModel): CloudsEntity = CloudsEntity(all = type.all)

}