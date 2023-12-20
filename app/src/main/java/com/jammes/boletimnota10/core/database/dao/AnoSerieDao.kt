package com.jammes.boletimnota10.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jammes.boletimnota10.core.database.entity.AnoSerie

@Dao
interface AnoSerieDao {

    @Insert
    suspend fun insert(anoSerie: AnoSerie)

    @Update
    suspend fun update(anoSerie: AnoSerie)

    @Delete
    suspend fun delete(anoSerie: AnoSerie)

    @Query("SELECT * FROM ano_serie")
    suspend fun fetchAnoSerie(): List<AnoSerie>

}