package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.CloudsConstants

/**
 * Model used solely for the caching of a [Clouds]
 */
@Entity(tableName = CloudsConstants.TABLE_NAME)
data class CachedClouds(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var all: Int? = null)
