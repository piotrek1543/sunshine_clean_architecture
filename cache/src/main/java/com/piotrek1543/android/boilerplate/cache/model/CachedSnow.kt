package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model used solely for the caching of a [Snow]
 */
@Entity(tableName = "snow")
data class CachedSnow(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var _3h: Double? = null)
