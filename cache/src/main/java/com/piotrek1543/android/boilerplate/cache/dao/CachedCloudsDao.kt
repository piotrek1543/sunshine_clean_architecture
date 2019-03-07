package com.piotrek1543.android.boilerplate.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.CloudsConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedClouds

@Dao
abstract class CachedCloudsDao {

    @Query(CloudsConstants.QUERY_CLOUDS)
    abstract fun getClouds(): List<CachedClouds>?

    @Query(CloudsConstants.DELETE_ALL_CLOUDS)
    abstract fun clearClouds()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertClouds(cachedClouds: List<CachedClouds>?)

}