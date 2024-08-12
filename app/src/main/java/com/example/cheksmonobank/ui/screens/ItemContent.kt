package com.example.cheksmonobank.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cheksmonobank.AppRoute
import com.example.cheksmonobank.AppScreen
import com.example.cheksmonobank.AppScreenEnvairement
import com.example.cheksmonobank.FloatingAction
import com.example.cheksmonobank.R
import com.example.cheksmonobank.injectViewModel
import com.example.navigation.ResponseListener
import com.example.navigation.Router
import com.example.navigation.localRouter


val ItemsScreenProducer = { ItemsScreen() }

class ItemsScreen: AppScreen {
    private var router: Router? = null
    override val environment = AppScreenEnvairement().apply {
        titleRes = R.string.items
        icon = Icons.Default.List
        floatingAction = FloatingAction(
            icon = Icons.Default.Add,
            onClick = {
                router?.launch(AppRoute.Item(ItemScreenArgs.Add))
            }
        )
    }
    @Composable
    override fun Content(){
        router = localRouter.current
        //val viewModel = viewModel<ItemsViewModel>()
        //  HILT
        val viewModel = injectViewModel<ItemsViewModel>()
        val items by viewModel.itemsFlow.collectAsStateWithLifecycle()
        val isEmpty by remember {
            derivedStateOf { items.isEmpty() }
        }
        ResponseListener (viewModel::processResponse)
        ItemsContent(isItemsEmpty = isEmpty, items = {items},
            onItemClicked = {index->
                router?.launch(AppRoute.Item(ItemScreenArgs.Edit(index)))
            })
    }
}

//@Composable
//fun ItemScreen(){
//
//}

@Composable
private fun ItemsContent(    isItemsEmpty: Boolean,
    items: ()-> List<String>,
    onItemClicked: (Int) -> Unit) {
    if (isItemsEmpty) {
        Text(
            text = stringResource(id = R.string.noItems),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
        )
    } else {
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){
            val itemsList = items()
            items(itemsList.size) { index ->
                Text(
                    text = itemsList[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClicked(index)
                        }
                        .padding(10.dp),
                )
            }
        }
    }

}

@Composable
@Preview(showSystemUi = true)
private fun PrewiewItemsScreen(){
    ItemsContent(items = {emptyList<String>()},
        isItemsEmpty = false,
        onItemClicked = {})

}