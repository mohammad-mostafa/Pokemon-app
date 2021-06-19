package com.mo.pokeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "SpeciesDetails")
data class SpeciesDetailsDbEntity(
    @PrimaryKey val url: String,
    @TypeConverters(SpeciesDetailsTypeConverter::class) val details: Details
)

data class Details(
    val name: String,
    val evolutionChainUrl: EvolutionChain,
    val flavors: List<FlavorsDb>
) {

    data class EvolutionChain(val url: String?)

    data class FlavorsDb(
        val flavorText: String,
        val lang: String
    )
}