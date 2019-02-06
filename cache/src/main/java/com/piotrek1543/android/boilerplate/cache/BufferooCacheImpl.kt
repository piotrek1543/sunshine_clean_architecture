package com.piotrek1543.android.boilerplate.cache

import com.piotrek1543.android.boilerplate.cache.db.SunshineDatabase
import com.piotrek1543.android.boilerplate.cache.mapper.BufferooEntityMapper
import com.piotrek1543.android.boilerplate.data.model.BufferooEntity
import com.piotrek1543.android.boilerplate.data.repository.BufferooCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Bufferoo instances. This class implements the
 * [BufferooCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class BufferooCacheImpl @Inject constructor(val sunshineDatabase: SunshineDatabase,
                                            private val entityMapper: BufferooEntityMapper,
                                            private val preferencesHelper: com.piotrek1543.android.boilerplate.cache.PreferencesHelper) :
        BufferooCache {


    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearBufferoos(): Completable {
        return Completable.defer {
            sunshineDatabase.cachedBufferooDao().clearBufferoos()
            Completable.complete()
        }
    }

    /**
     * Save the given list of [BufferooEntity] instances to the database.
     */
    override fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable {
        return Completable.defer {
            bufferoos.forEach {
                sunshineDatabase.cachedBufferooDao().insertBufferoo(
                        entityMapper.mapToCached(it))
            }
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [BufferooEntity] instances from the database.
     */
    override fun getBufferoos(): Flowable<List<BufferooEntity>> {
        return Flowable.defer {
            Flowable.just(sunshineDatabase.cachedBufferooDao().getBufferoos())
        }.map {
            it.map { entityMapper.mapFromCached(it) }
        }
    }

    /**
     * Check whether there are instances of [CachedBufferoo] stored in the cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(sunshineDatabase.cachedBufferooDao().getBufferoos().isNotEmpty())
        }
    }

    /**
     * Set a point in time at when the cache was last updated.
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > com.piotrek1543.android.boilerplate.cache.BufferooCacheImpl.Companion.EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

    companion object {
        private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
    }

}