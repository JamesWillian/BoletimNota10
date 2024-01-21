package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jammes.boletimnota10.core.database.entity.Periodo

@Dao
interface PeriodoDao {

    @Insert
    suspend fun inserir(periodo: Periodo)

    @Query("SELECT * FROM periodo WHERE turmaId = :turmaId")
    suspend fun buscarTodosPeriodosDaTurma(turmaId: String): List<Periodo>
}