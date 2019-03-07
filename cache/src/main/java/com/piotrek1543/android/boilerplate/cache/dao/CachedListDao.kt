package com.piotrek1543.android.boilerplate.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.ListConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedList

@Dao
abstract class CachedListDao {

    @Query(ListConstants.QUERY_LIST)
    abstract fun getList(): List<CachedList>?

    @Query(ListConstants.DELETE_ALL_LISTS)
    abstract fun clearList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(cachedList: List<CachedList>?)

}