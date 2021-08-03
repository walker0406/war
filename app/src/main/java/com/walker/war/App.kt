package com.walker.war

import android.app.Application
import com.sankuai.waimai.router.Router
import com.sankuai.waimai.router.common.DefaultRootUriHandler
import com.walker.war.di.qualifier.Test2
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by admin on 2021/7/10.
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // 创建RootHandler
        // 创建RootHandler
        val rootHandler = DefaultRootUriHandler(this)

// 初始化

// 初始化
        Router.init(rootHandler)
    }

    @Test2
    @Inject
    lateinit var urtl: Any

}