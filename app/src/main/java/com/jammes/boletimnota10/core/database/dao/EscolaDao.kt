package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jammes.boletimnota10.core.database.entity.Escola

@Dao
interface EscolaDao {

    @Insert
    suspend fun insert(escola: Escola)

    @Update
    suspend fun update(escola: Escola)

    @Delete
    suspend fun delete(escola: Escola)

    @Query("SELECT * FROM escola")
    suspend fun fetchEscolas(): List<Escola>

}