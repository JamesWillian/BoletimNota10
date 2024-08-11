package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "periodo")
data class Periodo(
    @PrimaryKey val id: String,
    val turmaId: String,
    val descricao: String
)