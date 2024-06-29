package com.jammes.boletimnota10.core.repository.api.responses

data class BoletimPeriodoResponse (
    val moduloId: String,
    val periodoId: String,
    val item: Int,
    val modulo: String,
    val professor: String,
    val notaTotal: Double
)
