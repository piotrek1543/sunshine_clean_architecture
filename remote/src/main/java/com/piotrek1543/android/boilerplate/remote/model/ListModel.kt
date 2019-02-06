package com.piotrek1543.android.boilerplate.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.piotrek1543.android.boilerplate.data.model.WindEntity

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
    var windEntity: WindEntity? = null
    @SerializedName("rain")
    @Expose
    var rain: RainModel? = null
    @SerializedName("snow")
    @Expose
    var snow: SnowModel? = null
    @SerializedName("sys")
    @Expose
    var podModel: PodModel? = null
    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null

}
