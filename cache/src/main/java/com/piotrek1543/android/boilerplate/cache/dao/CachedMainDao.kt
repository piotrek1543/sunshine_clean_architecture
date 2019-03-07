package com.piotrek1543.android.boilerplate.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.MainConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedMain

@Dao
abstract class CachedMainDao {

    @Query(MainConstants.QUERY_MAIN)
    abstract fun getMain(): List<CachedMain>?

    @Query(MainConstants.DELETE_ALL_MAINS)
    abstract fun clearMain()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMain(cachedMain: List<CachedMain>?)

}