package com.piotrek1543.android.boilerplate.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.WindConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedWind

@Dao
abstract class CachedWindDao {

    @Query(WindConstants.QUERY_WIND)
    abstract fun getWind(): List<CachedWind>?

    @Query(WindConstants.DELETE_ALL_WINDS)
    abstract fun clearWind()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWind(cachedWind: List<CachedWind>?)

}