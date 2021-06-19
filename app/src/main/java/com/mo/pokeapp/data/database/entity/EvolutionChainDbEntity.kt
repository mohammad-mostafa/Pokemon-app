package com.mo.pokeapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mo.pokeapp.data.database.SpeciesDetailsTypeConverter

@Entity(tableName = "EvolutionChain")
data class EvolutionChainDbEntity(
    @PrimaryKey val url: String,
    @TypeConverters(SpeciesDetailsTypeConverter::class) val list: List<SpeciesDb>
)
data class SpeciesDb(val name: String, val url: String, val photoUrl: String)