package com.hao.androidrecord.activity.physicslayout

import com.squareup.moshi.Json

data class Contributor (
    @Json(name = "avatar_url")
    var avatarUrl: String? = null
)
