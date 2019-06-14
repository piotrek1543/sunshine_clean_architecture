package com.piotrek1543.android.boilerplate.remote.model

import com.squareup.moshi.Json


class CoordModel(
        @field:Json(name = "lon")
        var lon: Double? = null,
        @field:Json(name = "lat")
        var lat: Double? = null,
        var cityId: Int? = null
)
