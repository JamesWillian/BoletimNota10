package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ano_serie")
data class AnoSerie(
    @PrimaryKey val uuid: String,
    val descricao: String
)