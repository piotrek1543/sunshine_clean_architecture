package com.piotrek1543.android.boilerplate.domain.repository

import com.piotrek1543.android.boilerplate.domain.model.Bufferoo
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface BufferooRepository {

    fun clearBufferoos(): Completable

    fun saveBufferoos(bufferoos: List<Bufferoo>): Completable

    fun getBufferoos(): Flowable<List<Bufferoo>>

}