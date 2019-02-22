package com.piotrek1543.android.boilerplate.remote.model

import com.squareup.moshi.Json

class CityModel {

    @field:Json(name = "id")
    var id: Int? = null
    @field:Json(name = "name")
    var name: String? = null
    @field:Json(name = "coord")
    var coord: CoordModel? = null
    @field:Json(name = "country")
    var country: String? = null
    @field:Json(name = "population")
    var population: Int? = null

}
