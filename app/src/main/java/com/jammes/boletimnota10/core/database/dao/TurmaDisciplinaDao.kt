package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jammes.boletimnota10.core.database.entity.TurmaDisciplina

@Dao
interface TurmaDisciplinaDao {

    @Insert
    suspend fun insert(turmaDisciplina: TurmaDisciplina)

    @Delete
    suspend fun delete(turmaDisciplina: TurmaDisciplina)

    @Query("SELECT * FROM turma_disciplina WHERE turmaId=:turmaId")
    suspend fun fetchTurmaDisciplina(turmaId: String): List<TurmaDisciplina>

}