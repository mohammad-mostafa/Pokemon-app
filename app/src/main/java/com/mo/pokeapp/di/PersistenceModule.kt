package com.mo.pokeapp.di

import android.content.Context
import com.mo.pokeapp.data.dao.SpeciesDetailsDbDao
import com.mo.pokeapp.data.database.PokeDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    fun provideWeatherDbDao(@ApplicationContext context: Context): SpeciesDetailsDbDao {
        return PokeDataBase.getInstance(context).speciesDetailsDbDao()
    }
}