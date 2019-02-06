package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model used solely for the caching of a [Sys]
 */
@Entity(tableName = "sys")
data class CachedSys(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var population: Int? = null)
