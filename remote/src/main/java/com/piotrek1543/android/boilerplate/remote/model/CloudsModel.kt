package com.piotrek1543.android.boilerplate.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CloudsModel {

    @SerializedName("all")
    @Expose
    var all: Int? = null

}