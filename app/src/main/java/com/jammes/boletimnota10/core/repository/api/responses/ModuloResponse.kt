package com.jammes.boletimnota10.core.repository.api.responses

data class ModuloResponse(
    val result: ModuloResult
)

data class ModuloResult(
    val periodoId: String,
    val disciplinaId: String,
    val item: String
)
