package com.example.cheksmonobank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.cheksmonobank.ui.ScaffoldFun
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.collections.immutable.persistentListOf


val RouteTabs = persistentListOf(AppRoute.Tab.Items, AppRoute.Tab.Settings, AppRoute.Tab.Profile)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScaffoldFun()
        }
    }

}




