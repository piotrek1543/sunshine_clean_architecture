package com.piotrek1543.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import com.piotrek1543.android.boilerplate.cache.db.constants.PodConstants

/**
 * Model used solely for the caching of a [Pod]
 */
@Entity(tableName = PodConstants.TABLE_NAME,
        foreignKeys = [ForeignKey(entity = CachedWind::class,
                parentColumns = ["listDt"],
                childColumns = ["listDt"],
                onDelete = ForeignKey.CASCADE)]
)
data class CachedPod(
        val listDt: Long? = null,
        var id: Int = 0,
        var pod: String? = null
)
