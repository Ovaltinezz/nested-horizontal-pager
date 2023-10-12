package com.ovt.nested_horizontal_pager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ovt.nested_horizontal_pager.ui.theme.NestedhorizontalpagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedhorizontalpagerTheme {
                ScrollableTabRowSimple()
            }
        }
    }
}