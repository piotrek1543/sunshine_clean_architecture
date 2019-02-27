package com.piotrek1543.android.boilerplate.remote.model

import com.squareup.moshi.Json

class PodModel {

    @field:Json(name = "pod")
    var pod: String? = null

    var listDt: Long? = null
}
