package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jammes.boletimnota10.core.database.entity.TipoAtividade

@Dao
interface TipoAtividadeDao {

    @Insert
    suspend fun insert(tipoAtividade: TipoAtividade)

    @Update
    suspend fun update(tipoAtividade: TipoAtividade)

    @Delete
    suspend fun delete(tipoAtividade: TipoAtividade)

    @Query("SELECT * FROM tipo_atividade")
    suspend fun fetchTipoAtividade(): List<TipoAtividade>

}