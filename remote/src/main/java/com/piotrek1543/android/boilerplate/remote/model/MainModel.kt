package com.piotrek1543.android.boilerplate.remote.model

import com.squareup.moshi.Json

class MainModel {

    @field:Json(name = "temp")
    var temp: Double? = null
    @field:Json(name = "temp_min")
    var tempMin: Double? = null
    @field:Json(name = "temp_max")
    var tempMax: Double? = null
    @field:Json(name = "pressure")
    var pressure: String? = null
    @field:Json(name = "sea_level")
    var seaLevel: String? = null
    @field:Json(name = "grnd_level")
    var grndLevel: String? = null
    @field:Json(name = "humidity")
    var humidity: Int? = null
    @field:Json(name = "temp_kf")
    var tempKf: String? = null

    @field:Json(name = "list_dt")
    var listDt: Long? = null

}
