package com.lmy.wanandroid_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lmy.wanandroid_compose.ui.navigation.Navigation
import com.lmy.wanandroid_compose.ui.theme.WanandroidcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanandroidcomposeTheme {
              Navigation()
            }
        }
    }
}


