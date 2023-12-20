package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jammes.boletimnota10.core.database.entity.Unidade

@Dao
interface UnidadeDao {

    @Insert
    suspend fun insert(unidade: Unidade)

    @Update
    suspend fun update(unidade: Unidade)

    @Delete
    suspend fun delete(unidade: Unidade)

    @Query("SELECT * FROM unidade")
    suspend fun fetchUnidade(): List<Unidade>

}