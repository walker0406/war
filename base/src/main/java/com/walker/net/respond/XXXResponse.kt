package com.walker.net.respond

import org.json.JSONObject
import java.io.Serializable

/**
 * Created by admin on 2021/8/13.
 */
class XXXResponse<out T>(val code: Int, val msg: String, val data: T) : Serializable {
    override fun toString(): String {
        return JSONObject().apply {
            put("code", code)
            put("msg", msg)
            put("data", data)
        }.toString()
    }
}