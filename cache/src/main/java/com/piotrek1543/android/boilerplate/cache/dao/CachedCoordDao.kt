package com.piotrek1543.android.boilerplate.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.CoordConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedCoord

@Dao
abstract class CachedCoordDao {

    @Query(CoordConstants.QUERY_COORD)
    abstract fun getCoord(): CachedCoord?

    @Query(CoordConstants.DELETE_ALL_COORDS)
    abstract fun clearCoord()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCoord(cachedCoord: CachedCoord?)

}