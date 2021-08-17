package com.walker.net.respond

import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import com.squareup.moshi.JsonAdapter

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import retrofit2.Response
import java.lang.reflect.Type


/**
 * Created by admin on 2021/8/13.
 */
class XXXListResponse<out T>(val data: List<T>) : Serializable {
//    override fun toString(): String
//        return JSONArray().apply {
//            put(data)
//        }.toString()
//
//    }
}