package com.mo.pokeapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mo.pokeapp.data.database.SpeciesDetailsDbEntity

@Dao
interface SpeciesDetailsDbDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: SpeciesDetailsDbEntity)

    @Query("SELECT * FROM SpeciesDetails WHERE url=:url LIMIT 1")
    suspend fun getDetails(url: String): SpeciesDetailsDbEntity
}