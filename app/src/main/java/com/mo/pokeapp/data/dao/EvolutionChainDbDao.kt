package com.mo.pokeapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mo.pokeapp.data.database.entity.EvolutionChainDbEntity

@Dao
interface EvolutionChainDbDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: EvolutionChainDbEntity)

    @Query("SELECT * FROM EvolutionChain WHERE url=:url LIMIT 1")
    suspend fun getEvolutionChain(url: String): EvolutionChainDbEntity
}