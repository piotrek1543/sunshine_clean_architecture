package org.buffer.android.boilerplate.data.model

/**
 * Representation for a [CityEntity] fetched from an external layer data source
 */
data class CityEntity(
        val id: Int,
        val name: String,
        val coordEntity: CoordEntity? = null,
        val country: String? = null,
        val population: Int? = null,
        val sys: SysEntity? = null
)
