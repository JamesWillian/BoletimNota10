package com.jammes.boletimnota10.core.repository.api.responses

data class BoletimModuloResponse (
    val result: List<BoletimModuloResult?>
)

data class BoletimModuloResult (
    val disciplina: String,
    val professor: String,
    val notaTotal: Double
)
