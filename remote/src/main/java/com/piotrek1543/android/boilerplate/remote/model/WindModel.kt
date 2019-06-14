package com.piotrek1543.android.boilerplate.remote.model

import com.squareup.moshi.Json

class WindModel(
        @field:Json(name = "speed")
        var speed: Double? = null,
        @field:Json(name = "deg")
        var deg: Float? = null,
        var listDt: Long? = null
)