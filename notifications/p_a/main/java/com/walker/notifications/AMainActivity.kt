package com.walker.modul1

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.sankuai.waimai.router.annotation.RouterUri
import timber.log.Timber

@RouterUri(path = ["/aaccount"])
class AMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("intent value=" + intent.getIntExtra("test", 8))
//        setContent {
//            theme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    AGreeting("Android")
//                }
//            }
//        }
    }
}

