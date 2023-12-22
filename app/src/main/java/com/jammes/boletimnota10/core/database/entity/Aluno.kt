package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aluno")
data class Aluno(
    @PrimaryKey val uuid: String,
    val nome: String
)