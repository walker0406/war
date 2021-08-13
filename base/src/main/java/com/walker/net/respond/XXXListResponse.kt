package com.walker.net.respond

import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import com.squareup.moshi.JsonAdapter

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type


/**
 * Created by admin on 2021/8/13.
 */
class XXXListResponse<out T>(val code: Int, val msg: String, val data: T) : Serializable {
    override fun toString(): String {
        return JSONObject().apply {
        }.toString()

    }
}