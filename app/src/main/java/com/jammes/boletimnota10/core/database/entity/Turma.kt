package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "turma")
data class Turma(
    @PrimaryKey val id: String,
    val nome: String,
    val escola: String,
    val turno: String,
    val ano: String,
    val dataInicio: String,
    val dataFinal: String? = "",
    val concluido: Boolean = false
)