package com.walker.modul1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sankuai.waimai.router.annotation.RouterUri
import timber.log.Timber

@RouterUri(path = ["/baccount"])
class BMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("intent value=" + intent.getIntExtra("test", 8))
    }
}
