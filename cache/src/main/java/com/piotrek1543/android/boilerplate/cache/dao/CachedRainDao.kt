package com.piotrek1543.android.boilerplate.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.RainConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedRain

@Dao
abstract class CachedRainDao {

    @Query(RainConstants.QUERY_RAIN)
    abstract fun getRain(): List<CachedRain>?

    @Query(RainConstants.DELETE_ALL_RAINS)
    abstract fun clearRain()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRain(cachedRain: List<CachedRain>?)

}