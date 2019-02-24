package com.piotrek1543.android.boilerplate.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.ListConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedList

@Dao
abstract class CachedListDao {

    @Query(ListConstants.QUERY_LIST)
    abstract fun getList(): CachedList?

    @Query(ListConstants.DELETE_ALL_LISTS)
    abstract fun clearList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertList(cachedList: CachedList)

}