package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jammes.boletimnota10.core.database.entity.Turma

@Dao
interface TurmaDao {

    @Insert
    suspend fun insert(turma: Turma)

    @Update
    suspend fun update(turma: Turma)

    @Delete
    suspend fun delete(turma: Turma)

    @Query("SELECT count(id) <> 0 FROM turma")
    suspend fun existeTurmaCadastrada(): Boolean?

    @Query("SELECT * FROM turma WHERE id = :turmaId")
    suspend fun selectTurmaPorId(turmaId: String): Turma

    @Query("SELECT * FROM turma")
    suspend fun selectTodasTurmas(): List<Turma>

}