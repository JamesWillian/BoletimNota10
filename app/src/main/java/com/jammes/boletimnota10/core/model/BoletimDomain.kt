package com.jammes.boletimnota10.core.model

data class BoletimDomain(
    val moduloId: String,
    val periodoId: String,
    val modulo: String,
    val professor: String? = "",
    val notaTotal: Float = 0.0f
)
