package com.example.cheksmonobank.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cheksmonobank.RouteTabs

@Composable
fun AppNavigationBar(currentIndex: Int,
                     onIndexSelected: (Int) -> Unit,
                     modifier: Modifier = Modifier) {

    NavigationBar(
        modifier = modifier
    ) {
        RouteTabs.forEachIndexed { index, tab ->
            val envairement = remember(tab) {
                tab.screenProducer().environment
            }
            val icon = envairement.icon
            NavigationBarItem(selected = currentIndex == index,
                label = { Text(text = stringResource(id = envairement.titleRes)) },
                onClick = {
                    onIndexSelected(index)
                },
                icon = {
                    if (icon != null) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Icon button"
                        )
                    }
                })
        }
    }
}