package com.mo.pokeapp.di

import com.mo.pokeapp.network.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    fun provideApiService(): APIService {
        return APIService.create()
    }
}