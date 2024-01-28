package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jammes.boletimnota10.core.database.entity.Avaliacao

@Dao
interface AvaliacaoDao {

    @Insert
    suspend fun insert(avaliacao: Avaliacao)

    @Update
    suspend fun update(avaliacao: Avaliacao)

    @Delete
    suspend fun delete(avaliacao: Avaliacao)

    @Query("SELECT * FROM avaliacao WHERE moduloId = :moduloId")
    suspend fun buscarAvaliacoes(moduloId: String): List<Avaliacao>

}