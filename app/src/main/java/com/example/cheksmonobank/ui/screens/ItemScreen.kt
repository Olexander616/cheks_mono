package com.example.cheksmonobank.ui.screens

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.cheksmonobank.AppRoute
import com.example.cheksmonobank.AppScreen
import com.example.cheksmonobank.AppScreenEnvairement
import com.example.cheksmonobank.R
import com.example.cheksmonobank.injectViewModel
import com.example.navigation.ResponseListener
import com.example.navigation.localRouter

import kotlinx.parcelize.Parcelize

fun ItemScreenProducer(args: ItemScreenArgs): () -> ItemScreen {
    return { ItemScreen(args) }
}

sealed class ItemScreenArgs : Parcelable {
    @Parcelize
    data object Add : ItemScreenArgs()

    @Parcelize
    data class Edit(val index: Int) : ItemScreenArgs()
}

data class ItemScreenResponse(
    val args: ItemScreenArgs,
    val newValue: String
)

class ItemScreen(
    private val args: ItemScreenArgs
) : AppScreen {
    override val environment = AppScreenEnvairement().apply {
        titleRes = if (args is ItemScreenArgs.Add) {
            R.string.add_item
        } else {
            R.string.edit_item
        }
    }


    @Composable
    override fun Content() {
        val router = localRouter.current
        //  HILT
        val viewModel = injectViewModel<ItemViewModel, ItemViewModel.Factory>{ factory ->
            factory.create(args)
        }

        ItemContent(
            initialValue = remember {
                viewModel.getInitialValue()
            },
            onSubmitNewItem = {
                router.pop(response = ItemScreenResponse(args, it))
            },
            isAddMode =
            args is ItemScreenArgs.Add,
            onLaunchSettingsScreen = {
                router.launch(AppRoute.Tab.Settings)
            }
        )
    }
}


@Composable
fun ItemContent(
    initialValue: String = "",
    isAddMode: Boolean = false,
    onSubmitNewItem: (String) -> Unit,
    onLaunchSettingsScreen: () -> Unit
) {
    var currentItemValue by rememberSaveable {
        mutableStateOf(initialValue)
    }
    val isAddEnabled by remember {
        derivedStateOf {
            currentItemValue.isNotEmpty()
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(value = currentItemValue,
            label = { Text(text = "Enter new value") },
            singleLine = true,
            onValueChange = { newValue ->
                currentItemValue = newValue
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            enabled = isAddEnabled,
            onClick = {
                onSubmitNewItem(currentItemValue)
            }) {
            val buttonName = if (isAddMode) {
                R.string.add_item
            } else {
                R.string.edit_item
            }
            Text(text = stringResource(id = buttonName))

        }
        Button(
            onClick = {
                onLaunchSettingsScreen()
            }) {
            Text(text = "Launch settings")

        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PrewiewItemScreen() {
    ItemContent(onSubmitNewItem = {}, onLaunchSettingsScreen = {})
}