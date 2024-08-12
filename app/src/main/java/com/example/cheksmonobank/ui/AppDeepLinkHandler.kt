package com.example.cheksmonobank.ui

import android.net.Uri
import com.example.navigation.links.DeepLinkHandler
import com.example.navigation.links.MultiStackState
import com.example.cheksmonobank.AppRoute
import com.example.cheksmonobank.ui.screens.ItemScreenArgs

object AppDeepLinkHandler: DeepLinkHandler{
    override fun handDeepLink(uri: Uri, inputState: MultiStackState): MultiStackState {
        var outptState = inputState
        if (uri.scheme == "nav"){
            if(uri.host == "settings"){
                outptState = inputState.copy(activeStackIndex = 1)
            }else if (uri.host == "items"){
                val itemIndex = uri.pathSegments?.firstOrNull()?.toIntOrNull()
                if (itemIndex != null){
                    val editItemRoute = AppRoute.Item(ItemScreenArgs.Edit(itemIndex))
                    outptState = inputState.withNewRoute(statckIndex = 0, editItemRoute)
                }
            }
        }
        return outptState
    }
}