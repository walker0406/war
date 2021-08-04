package com.walker.war

import android.app.Application
import android.util.Log
import com.sankuai.waimai.router.BuildConfig
import com.sankuai.waimai.router.Router
import com.sankuai.waimai.router.common.DefaultRootUriHandler
import com.sankuai.waimai.router.core.Debugger
import com.walker.war.di.qualifier.Test2
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by admin on 2021/7/10.
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //日志打印
        if (com.walker.war.BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        } else {
            Timber.plant(CrashReportingTree());
        }
        // 创建RootHandler
        val rootHandler = DefaultRootUriHandler(this)
        Router.init(rootHandler)
        // Log开关，建议测试环境下开启，方便排查问题。
        Debugger.setEnableLog(com.walker.war.BuildConfig.DEBUG);
        // 调试开关，建议测试环境下开启。调试模式下，严重问题直接抛异常，及时暴漏出来。
        Debugger.setEnableDebug(com.walker.war.BuildConfig.DEBUG);

    }

    class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }
        }
    }

}