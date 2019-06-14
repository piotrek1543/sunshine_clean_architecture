package com.piotrek1543.android.boilerplate.remote.model

import com.squareup.moshi.Json

class ForecastModel(
        @field:Json(name = "city")
        var city: CityModel? = null,
        @field:Json(name = "cod")
        var cod: String? = null,
        @field:Json(name = "message")
        var message: Double? = null,
        @field:Json(name = "cnt")
        var cnt: Int? = null,
        @field:Json(name = "list")
    var list: List<ListModel>? = null
)
