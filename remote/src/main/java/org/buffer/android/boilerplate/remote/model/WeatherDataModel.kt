package org.buffer.android.boilerplate.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.buffer.android.boilerplate.remote.model.CityModel
import org.buffer.android.boilerplate.remote.model.ListModel

class WeatherDataModel {

    @SerializedName("city")
    @Expose
    var city: CityModel? = null
    @SerializedName("cod")
    @Expose
    var cod: String? = null
    @SerializedName("message")
    @Expose
    var message: Double? = null
    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null
    @SerializedName("list")
    @Expose
    var list: kotlin.collections.List<ListModel>? = null

}
