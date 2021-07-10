package com.walker.war

import android.app.Application
import com.walker.war.di.qualifier.Test2
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by admin on 2021/7/10.
 */
@HiltAndroidApp
class App : Application() {

    @Test2
    @Inject
    lateinit var urtl: Any

}