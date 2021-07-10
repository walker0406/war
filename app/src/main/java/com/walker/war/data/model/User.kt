package com.walker.war.data.model

import com.squareup.moshi.Json

/**
 * Created by admin on 2021/7/10.
 */
data class User(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "avatar")
    val avatar: String = ""
)
