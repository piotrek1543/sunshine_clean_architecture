package com.piotrek1543.android.boilerplate.cache.mapper

import com.piotrek1543.android.boilerplate.cache.model.CachedList
import com.piotrek1543.android.boilerplate.data.model.ListEntity
import javax.inject.Inject

/**
 * Map a [CachedList] instance to and from a [ListEntity] instance when data is moving between
 * this later and the Data layer
 */
open class ListEntityMapper @Inject constructor() :
        EntityMapper<CachedList, ListEntity> {

    /**
     * Map a [ListEntity] instance to a [CachedList] instance
     */
    override fun mapToCached(type: ListEntity): CachedList = CachedList(
            dt = type.dt,
            dtTxt = type.dtTxt
    )

    /**
     * Map a [CachedList] instance to a [ListEntity] instance
     */
    override fun mapFromCached(type: CachedList): ListEntity = ListEntity(
            dt = type.dt,
            dtTxt = type.dtTxt
    )

}