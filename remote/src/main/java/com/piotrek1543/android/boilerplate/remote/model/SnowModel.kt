package com.piotrek1543.android.boilerplate.remote.model

import com.squareup.moshi.Json

class SnowModel(
        @field:Json(name = "3h")
        var _3h: Double? = null,
        var listDt: Long? = null
)
