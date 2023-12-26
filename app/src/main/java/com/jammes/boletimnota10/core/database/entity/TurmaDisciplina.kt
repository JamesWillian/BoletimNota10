package com.jammes.boletimnota10.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "turma_disciplina")
data class TurmaDisciplina(
    @PrimaryKey val uuid: String,
    val turmaId: String,
    val disciplinaId: String,
    val professor: String? = ""
)
