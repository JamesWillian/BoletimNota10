package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jammes.boletimnota10.core.database.entity.Disciplina

@Dao
interface DisciplinaDao {

    @Insert
    suspend fun insert(disciplina: Disciplina)

    @Update
    suspend fun update(disciplina: Disciplina)

    @Delete
    suspend fun delete(disciplina: Disciplina)

    @Query("SELECT * FROM disciplina")
    suspend fun fetchDisciplina(): List<Disciplina>

    @Query("SELECT * FROM disciplina WHERE id = :disciplinaId")
    suspend fun fetchDisciplinaById(disciplinaId: String): Disciplina
}