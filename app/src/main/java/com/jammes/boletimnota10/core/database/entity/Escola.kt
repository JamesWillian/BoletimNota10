package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "escola")
data class Escola(
    @PrimaryKey val uuid: String,
    val nome: String
)