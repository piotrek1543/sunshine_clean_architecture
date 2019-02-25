package com.piotrek1543.android.boilerplate.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.piotrek1543.android.boilerplate.cache.db.constants.CityConstants
import com.piotrek1543.android.boilerplate.cache.model.CachedCity

@Dao
abstract class CachedCityDao {

    @Query(CityConstants.QUERY_CITY)
    abstract fun getCity(): CachedCity?

    @Query(CityConstants.DELETE_ALL_CITIES)
    abstract fun clearCity()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCity(cachedCity: CachedCity?)

}