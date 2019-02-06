package com.piotrek1543.android.boilerplate.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CityModel {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("coord")
    @Expose
    var coord: CoordModel? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("population")
    @Expose
    var population: Int? = null
    @SerializedName("sys")
    @Expose
    var sys: SysModel? = null

}
