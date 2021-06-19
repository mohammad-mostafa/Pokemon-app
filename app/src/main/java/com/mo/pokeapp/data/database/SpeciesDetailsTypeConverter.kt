package com.mo.pokeapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mo.pokeapp.data.database.entity.SpeciesDb

class SpeciesDetailsTypeConverter {

    @TypeConverter
    fun detailToJson(details: Details): String = Gson().toJson(details)

    @TypeConverter
    fun detailsFromJson(json: String): Details = Gson().fromJson(json, Details::class.java)

    @TypeConverter
    fun evolutionChainToJson(innerChainDb: List<SpeciesDb>) = Gson().toJson(innerChainDb)

    @TypeConverter
    fun evolutionFromJson(json: String): List<SpeciesDb> {
        val type = object : TypeToken<List<SpeciesDb>>() {}.type
        return Gson().fromJson<List<SpeciesDb>>(json, type)
    }
}