package com.walker.war.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by admin on 2021/7/10.
 */
data class User(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "avatar")
    val avatar: String = ""
)
