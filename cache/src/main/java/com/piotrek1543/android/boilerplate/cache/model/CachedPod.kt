package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.db.constants.PodConstants

/**
 * Model used solely for the caching of a [Pod]
 */
@Entity(tableName = PodConstants.TABLE_NAME)
data class CachedPod(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var pod: String? = null)
