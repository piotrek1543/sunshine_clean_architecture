package com.piotrek1543.android.boilerplate.remote.model

import com.squareup.moshi.Json


class CloudsModel(
        @field:Json(name = "all")
        var all: Int? = null,
        var listDt: Long? = null
)
