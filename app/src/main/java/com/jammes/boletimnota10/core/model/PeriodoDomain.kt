package com.jammes.boletimnota10.core.model

import androidx.room.PrimaryKey

data class PeriodoDomain(
    val id: String,
    val turmaId: String,
    val descricao: String
)
