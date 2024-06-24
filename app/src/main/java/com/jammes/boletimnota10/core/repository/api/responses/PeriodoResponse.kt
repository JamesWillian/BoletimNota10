package com.jammes.boletimnota10.core.repository.api.responses

data class PeriodoResponse (
    val result: PeriodoResult
)

data class PeriodoResult (
    val id: String,
    val descricao: String,
    val turmaId: String
)

