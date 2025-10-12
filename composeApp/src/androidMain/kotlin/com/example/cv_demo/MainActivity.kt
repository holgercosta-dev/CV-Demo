package com.example.cv_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cv_demo.data.remote.AppClient
import com.example.cv_demo.di.appModule
import org.koin.android.ext.android.get
import org.koin.core.context.startKoin
//import com.example.cv_demo.BuildConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        startKoin {
//            androidContext(this@CvDemoApplication)
//
//            // Only log in debug builds
//            if (BuildConfig.DEBUG) {
//                androidLogger()
//            }
            modules(appModule)
        }

        setContent {
            App()
            val appClient = get<AppClient>()
            appClient.makeRequest()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}