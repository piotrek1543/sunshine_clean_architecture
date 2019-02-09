package com.piotrek1543.android.boilerplate.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListModel {

    @SerializedName("dt")
    @Expose
    var dt: Int? = null
    @SerializedName("main")
    @Expose
    var mainModel: MainModel? = null
    @SerializedName("weather")
    @Expose
    var weatherModel: kotlin.collections.List<WeatherModel>? = null
    @SerializedName("clouds")
    @Expose
    var cloudsModel: CloudsModel? = null
    @SerializedName("wind")
    @Expose
    var windModel: WindModel? = null
    @SerializedName("rainModel")
    @Expose
    var rainModel: RainModel? = null
    @SerializedName("snowModel")
    @Expose
    var snowModel: SnowModel? = null
    @SerializedName("sys")
    @Expose
    var podModel: PodModel? = null
    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null

}
