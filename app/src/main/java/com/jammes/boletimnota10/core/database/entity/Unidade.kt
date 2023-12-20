package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unidade")
data class Unidade(
    @PrimaryKey val uuid: String,
    val descricao: String
)
