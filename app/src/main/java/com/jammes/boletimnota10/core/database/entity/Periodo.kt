package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "periodo")
data class Periodo(
    @PrimaryKey val uuid: String,
    val turmaId: String,
    val descricao: String
)