package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "professor")
data class Professor(
    @PrimaryKey val uuid: String,
    val nome: String,
    val disciplinaId: String
)
