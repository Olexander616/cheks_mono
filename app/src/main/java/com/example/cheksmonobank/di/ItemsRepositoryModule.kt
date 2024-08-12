package com.example.cheksmonobank


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ItemsRepositoryModule {

    @Binds
    fun itemRepository(impl: ItemsRepositoryImpl): ItemsRepository
}