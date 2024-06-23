package com.jammes.boletimnota10.core.repository.api.responses

data class TurmaResponse(
    val result: TurmaResult
)

data class TurmaResult(
    val nome: String,
    val escola: String,
    val turno: String,
    val ano: String,
    val dataInicio: String,
    val id: String
)
