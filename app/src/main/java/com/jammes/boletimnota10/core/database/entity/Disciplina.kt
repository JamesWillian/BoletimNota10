package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disciplina")
data class Disciplina(
    @PrimaryKey val id: String,
    val descricao: String
)
