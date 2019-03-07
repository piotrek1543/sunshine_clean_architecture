package com.piotrek1543.android.boilerplate.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.SnowConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedSnow

@Dao
abstract class CachedSnowDao {

    @Query(SnowConstants.QUERY_SNOW)
    abstract fun getSnow(): List<CachedSnow>?

    @Query(SnowConstants.DELETE_ALL_SNOWS)
    abstract fun clearSnow()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSnow(cachedSnow: List<CachedSnow>?)

}