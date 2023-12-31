package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "turma")
data class Turma(
    @PrimaryKey val uuid: String,
    val nome: String,
    val escola: String,
    val periodo: String,
    val turno: String,
    val ano: Int,
    val concluido: Boolean = false
)