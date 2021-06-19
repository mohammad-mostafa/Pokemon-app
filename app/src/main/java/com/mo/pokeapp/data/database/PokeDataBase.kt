package com.mo.pokeapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mo.pokeapp.data.dao.EvolutionChainDbDao
import com.mo.pokeapp.data.dao.SpeciesDetailsDbDao
import com.mo.pokeapp.data.database.entity.EvolutionChainDbEntity

@TypeConverters(SpeciesDetailsTypeConverter::class)
@Database(
    entities = [SpeciesDetailsDbEntity::class, EvolutionChainDbEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PokeDataBase : RoomDatabase() {

    abstract fun speciesDetailsDbDao(): SpeciesDetailsDbDao
    abstract fun evolutionChainDbDao(): EvolutionChainDbDao

    companion object {

        private const val DATABASE_NAME = "POKE_DB"

        @Volatile
        private var instance: PokeDataBase? = null

        fun getInstance(context: Context): PokeDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): PokeDataBase {
            return Room.databaseBuilder(context, PokeDataBase::class.java, DATABASE_NAME)
                .build()
        }
    }
}