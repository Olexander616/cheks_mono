package com.example.cheksmonobank


import com.example.navigation.Route
import com.example.cheksmonobank.ui.screens.ItemScreenProducer
import com.example.cheksmonobank.ui.screens.ItemScreenArgs
import com.example.cheksmonobank.ui.screens.ItemsScreenProducer
import com.example.cheksmonobank.ui.screens.ProfileScreenProducer
import com.example.cheksmonobank.ui.screens.SettingsScreenProducer
import com.example.cheksmonobank.AppScreen
import kotlinx.parcelize.Parcelize

sealed class AppRoute(
    override val screenProducer: () -> AppScreen
    //@StringRes val title: Int
) : Route {

    @Parcelize
    data class Item (
        val args: ItemScreenArgs
    ): AppRoute(ItemScreenProducer(args))

    sealed class Tab(
//        @StringRes titleRes: Int,
//        val icon: ImageVector
        screenProducer: () -> AppScreen
    ) : AppRoute(screenProducer) {

        //        @Parcelize
//        data object Items : Tab(R.string.items, Icons.Default.List)
//        @Parcelize
//        data object Settings : Tab(R.string.settings, Icons.Default.Settings)
//        @Parcelize
//        data object Profile : Tab(R.string.profile, Icons.Default.AccountBox)
        @Parcelize
        data object Items : Tab(ItemsScreenProducer)

        @Parcelize
        data object Settings : Tab(SettingsScreenProducer)

        @Parcelize
        data object Profile : Tab(ProfileScreenProducer)
    }
}