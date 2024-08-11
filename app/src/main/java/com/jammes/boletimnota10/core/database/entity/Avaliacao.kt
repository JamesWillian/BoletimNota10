package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "avaliacao")
data class Avaliacao(
    @PrimaryKey val id: String,
    val moduloId: String,
    val descricao: String,
    val nota: Float,
    val data: String,
    val recuperacao: Boolean
)
