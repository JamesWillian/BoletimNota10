package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jammes.boletimnota10.core.database.entity.Professor

@Dao
interface ProfessorDao {

    @Insert
    suspend fun insert(professor: Professor)

    @Update
    suspend fun update(professor: Professor)

    @Delete
    suspend fun delete(professor: Professor)

    @Query("SELECT * FROM professor")
    suspend fun fetchProfessor(): List<Professor>

}