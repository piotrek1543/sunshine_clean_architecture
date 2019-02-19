package com.piotrek1543.android.boilerplate.remote.model

import com.squareup.moshi.Json

class ListModel {

    @field:Json(name = "dt")
    var dt: Long? = null
    @field:Json(name = "main")
    var mainModel: MainModel? = null
    @field:Json(name = "weather")
    var weatherModel: List<WeatherModel>? = null
    @field:Json(name = "clouds")
    var cloudsModel: CloudsModel? = null
    @field:Json(name = "wind")
    var windModel: WindModel? = null
    @field:Json(name = "rainModel")
    var rainModel: RainModel? = null
    @field:Json(name = "snowModel")
    var snowModel: SnowModel? = null
    @field:Json(name = "sys")
    var podModel: PodModel? = null
    @field:Json(name = "dt_txt")
    var dtTxt: String? = null

}
