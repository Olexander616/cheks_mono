package com.example.cheksmonobank.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.cheksmonobank.AppScreen
import com.example.cheksmonobank.AppScreenEnvairement
import com.example.cheksmonobank.R


val SettingsScreenProducer = { SettingsScreen() }

class SettingsScreen : AppScreen {
    override val environment = AppScreenEnvairement().apply {
        titleRes = R.string.settings
        icon = Icons.Default.Settings
    }


    @Composable
    override fun Content() {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Settings screen", fontSize = 28.sp)
        }
    }
}

//@Composable
//fun SettingsScreen() {
//
//}