package org.buffer.android.boilerplate.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PodModel {

    @SerializedName("pod")
    @Expose
    var pod: String? = null

}
