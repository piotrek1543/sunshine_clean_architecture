package com.piotrek1543.android.boilerplate.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherModel {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("main")
    @Expose
    var main: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("icon")
    @Expose
    var icon: String? = null

}
