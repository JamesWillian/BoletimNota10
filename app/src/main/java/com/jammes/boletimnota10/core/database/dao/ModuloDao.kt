package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jammes.boletimnota10.core.database.entity.Modulo

@Dao
interface ModuloDao {

    @Insert
    suspend fun inserir(modulo: Modulo)

    @Delete
    suspend fun delete(modulo: Modulo)

    @Query("SELECT * FROM modulo WHERE periodoId=:periodoId")
    suspend fun buscarTodosModulosDoPeriodo(periodoId: String): List<Modulo>

    @Query("SELECT * FROM modulo WHERE periodoId=:periodoId AND disciplinaId=:disciplinaId")
    suspend fun buscarModulo(periodoId: String, disciplinaId: String): Modulo?
}