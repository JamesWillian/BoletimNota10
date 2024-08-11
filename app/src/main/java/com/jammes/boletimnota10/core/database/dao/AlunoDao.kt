package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jammes.boletimnota10.core.database.entity.Aluno

@Dao
interface AlunoDao {

    @Insert
    suspend fun insert(aluno: Aluno)

    @Update
    suspend fun update(aluno: Aluno)

    @Query("SELECT * FROM aluno LIMIT 1")
    suspend fun fetchAluno(): Aluno

    @Query("SELECT id FROM aluno LIMIT 1")
    suspend fun buscarIdAluno(): String
}