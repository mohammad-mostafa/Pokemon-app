package com.mo.pokeapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class SpeciesDetailsTypeConverter {

    @TypeConverter
    fun toJson(details: Details): String = Gson().toJson(details)

    @TypeConverter
    fun fromJson(json: String): Details = Gson().fromJson(json, Details::class.java)
}