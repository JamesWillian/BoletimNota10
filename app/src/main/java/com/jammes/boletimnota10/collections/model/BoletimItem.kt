package com.jammes.boletimnota10.collections.model

data class BoletimItem(
    val moduloId: String,
    val periodoId: String,
    val modulo: String,
    val notaTotal: String,
    val professor: String?
)
