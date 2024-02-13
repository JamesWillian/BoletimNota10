package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.jammes.boletimnota10.core.database.entity.Boletim

@Dao
interface BoletimDao {

    @Query("SELECT * FROM boletim WHERE periodoId = :periodoId")
    suspend fun buscarBoletim(periodoId: String): List<Boletim>

    @Query("SELECT * FROM boletim WHERE moduloId = :moduloId")
    suspend fun buscarBoletimDoModulo(moduloId: String): Boletim?
}