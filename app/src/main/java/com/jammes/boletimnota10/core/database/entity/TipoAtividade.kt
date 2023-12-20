package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tipo_atividade")
data class TipoAtividade(
    @PrimaryKey val uuid: String,
    val descricao: String
)
