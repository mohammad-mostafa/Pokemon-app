package com.mo.pokeapp.di

import android.content.Context
import com.mo.pokeapp.data.dao.EvolutionChainDbDao
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
    fun provideSpeciesDetailsDbDao(@ApplicationContext context: Context): SpeciesDetailsDbDao {
        return PokeDataBase.getInstance(context).speciesDetailsDbDao()
    }

    @Provides
    fun provideEvolutionChainDbDao(@ApplicationContext context: Context): EvolutionChainDbDao {
        return PokeDataBase.getInstance(context).evolutionChainDbDao()
    }
}