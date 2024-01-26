package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "modulo")
data class Modulo(
    @PrimaryKey val uuid: String,
    val periodoId: String,
    val disciplinaId: String,
    val professor: String? = ""
)
