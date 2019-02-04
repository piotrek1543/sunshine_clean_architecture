package org.buffer.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model used solely for the caching of a [Pod]
 */
@Entity(tableName = "pod")
data class CachedPod(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var pod: String? = null)
