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
import com.walker.modul1.ui.theme.WarTheme
import timber.log.Timber

@RouterUri(path = ["/aaccount"])
class AMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("intent value=" + intent.getIntExtra("test", 8))
        setContent {
            WarTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AGreeting("Android")
                }
            }
        }
    }
}

@Composable
fun AGreeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun ADefaultPreview() {
    WarTheme {
        AGreeting("Android")
    }
}