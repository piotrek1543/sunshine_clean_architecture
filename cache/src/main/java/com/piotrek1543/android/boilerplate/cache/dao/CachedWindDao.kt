package com.piotrek1543.android.boilerplate.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.WindConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedWind

@Dao
abstract class CachedWindDao {

    @Query(WindConstants.QUERY_WIND)
    abstract fun getWind(): CachedWind?

    @Query(WindConstants.DELETE_ALL_WINDS)
    abstract fun clearWind()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWind(cachedWind: CachedWind)

}