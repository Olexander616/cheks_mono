package com.example.cheksmonobank.ui.screens

import androidx.lifecycle.ViewModel
import com.example.cheksmonobank.ItemsRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: ItemsRepository,
) : ViewModel(){

    val itemsFlow = repository.getItems()

    fun processResponse(response: ItemScreenResponse){
        when(response.args){
            is ItemScreenArgs.Add -> repository.addItem(response.newValue)
            is ItemScreenArgs.Edit -> repository.updateItem(response.args.index,response.newValue)
        }
    }
}