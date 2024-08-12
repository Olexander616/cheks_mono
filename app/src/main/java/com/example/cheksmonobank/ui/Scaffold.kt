package com.example.cheksmonobank.ui

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.navigation.navigationHost
import com.example.navigation.rememberNavigation
import com.example.cheksmonobank.AppRoute
import com.example.cheksmonobank.AppScreenEnvairement
import com.example.cheksmonobank.FloatingAction
import com.example.cheksmonobank.ItemsRepository
import com.example.cheksmonobank.MainViewModel
import com.example.cheksmonobank.R
import com.example.cheksmonobank.RouteTabs
import com.example.cheksmonobank.injectViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldFun() {

    val viewModel = injectViewModel<MainViewModel>()
    val navigation = rememberNavigation(RouteTabs, deepLinkHandler = AppDeepLinkHandler)
    val (router, navigationState) = navigation
    val envairement= navigationState.currentScreen.environment as AppScreenEnvairement

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(envairement.titleRes), fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { if (!navigationState.isRoot) router.pop() }) {
                        Icon(
                            imageVector = if (navigationState.isRoot)
                                Icons.Default.Menu else
                                Icons.Default.ArrowBack, contentDescription = "Main menu"
                        )
                    }
                },
                actions = {
                    var showPopupMenu by remember {
                        mutableStateOf(false)
                    }
                    val context = LocalContext.current
                    IconButton(onClick = { showPopupMenu = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Settings")
                    }
                    DropdownMenu(expanded = showPopupMenu,
                        onDismissRequest = {
                            showPopupMenu = false
                        }) {
                        DropdownMenuItem(text = { Text(text = "About") },
                            onClick = {
                                Toast.makeText(context, "Name of App", Toast.LENGTH_SHORT).show()
                                showPopupMenu = false
                            },
                            trailingIcon = {
                                Icon(imageVector = Icons.Default.Build, contentDescription = "1")
                            })

                        DropdownMenuItem(text = { Text(text = "Clear") },
                            onClick = {
                                viewModel::clear
                                showPopupMenu = false
                            },
//                            leadingIcon = {
//                                Icon(imageVector = Icons.Default.Call, contentDescription = "2")
//                            }
                            trailingIcon = {
                                Icon(imageVector = Icons.Default.Call, contentDescription = "2")
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            val floatingAction = envairement.floatingAction
            if (floatingAction != null) {
                FloatingActionButton(onClick = floatingAction.onClick) {
                    Icon(imageVector = floatingAction.icon, contentDescription = "Add")
                }
            }
//            if (navigationState.currentRoot == AppRoute.Tab.Items) {
//                FloatingActionButton(onClick = { router.launch(AppRoute.AddItem) }) {
//                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
//                }
//            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            //if (navigationState.isRoot) {
                AppNavigationBar(currentIndex = navigationState.currentStackIndex,
                    onIndexSelected = router::switchStack)
           // }
        }
    ) { paddingvalue ->
        navigationHost(
            navigation = navigation,
            modifier = Modifier
                .padding(paddingvalue)
                .fillMaxSize()
        )
//        { currentRoute ->
//            when (currentRoute) {
//                AppRoute.Tab.Items -> ItemScreen()
//                AppRoute.Tab.Settings -> SettingsScreen()
//                AppRoute.Tab.Profile -> Profilecreen()
//                AppRoute.AddItem -> {
//                    AddItemScreen()
//                }
//            }
//        }

    }
}