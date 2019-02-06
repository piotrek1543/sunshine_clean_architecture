package com.piotrek1543.android.boilerplate.remote

import com.piotrek1543.android.boilerplate.data.model.BufferooEntity
import com.piotrek1543.android.boilerplate.data.repository.BufferooRemote
import com.piotrek1543.android.boilerplate.remote.mapper.BufferooEntityMapper
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [BufferooRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class BufferooRemoteImpl @Inject constructor(private val bufferooService: BufferooService,
                                             private val entityMapper: BufferooEntityMapper) :
        BufferooRemote {

    /**
     * Retrieve a list of [BufferooEntity] instances from the [BufferooService].
     */
    override fun getBufferoos(): Flowable<List<BufferooEntity>> {
        return bufferooService.getBufferoos()
                .map { it.team }
                .map {
                    val entities = mutableListOf<BufferooEntity>()
                    it.forEach { entities.add(entityMapper.mapFromRemote(it)) }
                    entities
                }
    }

}