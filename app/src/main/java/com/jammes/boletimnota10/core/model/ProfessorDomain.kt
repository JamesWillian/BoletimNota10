package com.jammes.boletimnota10.core.model

import com.jammes.boletimnota10.core.database.entity.Disciplina

data class ProfessorDomain(
    val id: String,
    val nome: String,
    val disciplina: Disciplina
)
