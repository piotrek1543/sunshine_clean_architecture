package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.RainConstants

/**
 * Model used solely for the caching of a [Rain]
 */
@Entity(tableName = RainConstants.TABLE_NAME)
data class CachedRain(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = 0,
        var _3h: Double? = null)
