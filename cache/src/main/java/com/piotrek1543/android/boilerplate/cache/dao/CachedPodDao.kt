package com.piotrek1543.android.boilerplate.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.PodConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedPod

@Dao
abstract class CachedPodDao {

    @Query(PodConstants.QUERY_POD)
    abstract fun getPod(): List<CachedPod>?

    @Query(PodConstants.DELETE_ALL_PODS)
    abstract fun clearPod()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPod(cachedPod: List<CachedPod>?)

}