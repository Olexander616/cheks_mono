package com.example.cheksmonobank.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.cheksmonobank.AppScreen
import com.example.cheksmonobank.AppScreenEnvairement
import com.example.cheksmonobank.R


val ProfileScreenProducer = { ProfilerScreen() }

class ProfilerScreen : AppScreen {
    override val environment = AppScreenEnvairement().apply {
        titleRes = R.string.profile
        icon = Icons.Default.AccountBox
    }


    @Composable
    override fun Content() {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Profile screen", fontSize = 28.sp)
        }
    }
}


//@Composable
//fun Profilecreen() {
//
//}