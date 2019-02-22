package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.SnowConstants

/**
 * Model used solely for the caching of a [Snow]
 */
@Entity(tableName = SnowConstants.TABLE_NAME)
data class CachedSnow(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var _3h: Double? = null)
