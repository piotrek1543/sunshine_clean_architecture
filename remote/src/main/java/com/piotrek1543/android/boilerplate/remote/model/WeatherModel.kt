package com.piotrek1543.android.boilerplate.remote.model

import com.squareup.moshi.Json

class WeatherModel {

    @field:Json(name = "id")
    var id: Int? = null
    @field:Json(name = "main")
    var main: String? = null
    @field:Json(name = "description")
    var description: String? = null
    @field:Json(name = "icon")
    var icon: String? = null

    var listDt: Long? = null
}
