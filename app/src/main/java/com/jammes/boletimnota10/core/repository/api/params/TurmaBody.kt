package com.jammes.boletimnota10.core.repository.api.params

import java.util.Date

data class TurmaBody(
    val nome: String,
    val escola: String,
    val turno: String,
    val ano: String,
    val dataInicio: Date,
    val alunoId: String
)
